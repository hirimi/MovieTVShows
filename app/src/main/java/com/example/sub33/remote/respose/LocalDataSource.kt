package com.example.sub33.remote.respose

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.room.Dao

class LocalDataSource private constructor(private val dao: Dao) {

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = dao.movies()

    fun getTvShows(): DataSource.Factory<Int, TvShowsEntity> = dao.tvShows()

    fun getMoviesWatchList(): DataSource.Factory<Int, MovieEntity> = dao.moviesWatchlist()

    fun getTvShowsWatchList(): DataSource.Factory<Int, TvShowsEntity> = dao.tvShowsWatchlist()

    fun getMoviesByID(id: Int): LiveData<MovieEntity> = dao.moviesID(id)

    fun getTvShowsByID(id: Int): LiveData<TvShowsEntity> = dao.tvShowsID(id)

    fun insertMovies(moviesEntity: List<MovieEntity>) = dao.insertMovies(moviesEntity)

    fun insertTvShows(tvShowsEntity: ArrayList<TvShowsEntity>) = dao.insertTvShows(tvShowsEntity)

    fun updateMoviesWatchlist(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.apply {
            movielist = newState
            dao.updateMovies(this)
        }
    }

    fun updateTvShowsWatchlist(tvShowsEntity: TvShowsEntity, newState: Boolean) {
        tvShowsEntity.apply {
            tvlist = newState
            dao.updateTvShows(this)
        }
    }

    companion object {
        private var localDataSource: LocalDataSource? = null

        fun getLocalDataSource(dao: Dao): LocalDataSource {
            return localDataSource ?: LocalDataSource(dao).apply {
                localDataSource = this
            }
        }
    }
}