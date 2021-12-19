package com.example.sub33.remote.respose

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.remote.respose.movie.MovieDetailResponse
import com.example.sub33.remote.respose.movie.MovieRemote
import com.example.sub33.remote.respose.tv.TvShowsDetailResponse
import com.example.sub33.remote.respose.tv.TvShowsRemote
import com.example.sub33.source.RemoteDataSource
import com.example.sub33.utils.AppExecutors
import com.example.sub33.vo.Resource
import java.lang.NullPointerException

class Repository private constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors) : DataSource {

    override fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieRemote>>(appExecutors) {
            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build().also {
                        return LivePagedListBuilder(localDataSource.getMovies(), it).build()
                    }
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieRemote>>> {
                return remoteDataSource.getMoviesList()
            }

            override fun saveCallResult(data: List<MovieRemote>) {
                val moviesList = ArrayList<MovieEntity>()

                for (moviesData in data) {
                    moviesData.apply {
                        MovieEntity(
                            id,
                            poster,
                            title,
                            rating,
                            releaseDate,
                            synopsis,
                            false
                        ).also {
                            moviesList.add(it)
                        }
                    }
                }
                localDataSource.insertMovies(moviesList)
            }
        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowsEntity>, List<TvShowsRemote>>(appExecutors) {
            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<PagedList<TvShowsEntity>> {
                PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build().also {
                        return LivePagedListBuilder(localDataSource.getTvShows(), it).build()
                    }
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowsRemote>>> {
                return remoteDataSource.getTvShowsList()
            }

            override fun saveCallResult(data: List<TvShowsRemote>) {
                val tvShowsList = ArrayList<TvShowsEntity>()
                    for (tvShowsData in data) {
                        tvShowsData.apply {
                            try {
                                TvShowsEntity(
                                    id,
                                    poster,
                                    title,
                                    rating,
                                    releaseDate,
                                    synopsis,
                                    false
                                ).also {
                                    tvShowsList.add(it)
                                }
                            } catch (ignored: NullPointerException) {

                            }
                        }
                    }
                localDataSource.insertTvShows(tvShowsList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(moviesID: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<MovieEntity> {
                return localDataSource.getMoviesByID(moviesID)
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getMoviesDetails(moviesID.toString())
            }

            override fun saveCallResult(data: MovieDetailResponse) {
                data.apply {
                    MovieEntity(
                        id = id,
                        poster = poster,
                        title = title,
                        rating = rating,
                        releaseDate = releaseDate,
                        synopsis = sinopsis,
                        movielist = false
                    ).also {
                        localDataSource.updateMoviesWatchlist(it, false)
                    }
                }
            }
        }.asLiveData()
    }

    override fun getTvShowsDetail(tvShowsID: Int): LiveData<Resource<TvShowsEntity>> {
        return object : NetworkBoundResource<TvShowsEntity, TvShowsDetailResponse>(appExecutors) {
            override fun shouldFetch(data: TvShowsEntity?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<TvShowsEntity> {
                return localDataSource.getTvShowsByID(tvShowsID)
            }

            override fun createCall(): LiveData<ApiResponse<TvShowsDetailResponse>> {
                return remoteDataSource.getTvShowsDetails(tvShowsID.toString())
            }

            override fun saveCallResult(data: TvShowsDetailResponse) {
                data.apply {
                    TvShowsEntity(
                        id = id,
                        poster = poster,
                        title = title,
                        rating = rating,
                        releaseDate = releaseDate,
                        synopsis = synopsis,
                        tvlist = false
                    ).also {
                        localDataSource.updateTvShowsWatchlist(it, false)
                    }
                }
            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(moviesEntity: MovieEntity, state: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.updateMoviesWatchlist(moviesEntity, state)
        }
    }

    override fun setFavoriteTvShows(tvShowsEntity: TvShowsEntity, state: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.updateTvShowsWatchlist(tvShowsEntity, state)
        }
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build().also {
                return LivePagedListBuilder(localDataSource.getMoviesWatchList(), it).build()
            }
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowsEntity>> {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build().also {
                return LivePagedListBuilder(localDataSource.getTvShowsWatchList(), it).build()
            }
    }

    companion object {
        @Volatile
        private var repository: Repository? = null

        fun getRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): Repository {
            return repository ?: synchronized(this) {
                Repository(remoteDataSource, localDataSource, appExecutors).apply {
                    repository = this
                }
            }
        }
    }
}