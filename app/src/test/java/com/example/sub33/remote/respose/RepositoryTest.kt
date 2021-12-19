package com.example.sub33.remote.respose

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.source.RemoteDataSource
import com.example.sub33.utils.AppExecutors
import com.example.sub33.utils.Data
import com.example.sub33.utils.DataDetail
import com.example.sub33.utils.*
import com.example.sub33.vo.Resource
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@Suppress("UNCHECKED_CAST")
class RepositoryTest {
    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val localDataSource = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val fakeRepository = FakeRepository(remoteDataSource, localDataSource, appExecutors)

    private val moviesRemote = Data.getMoviesRemote()
    private val tvShowsRemote = Data.getTvShowsRemote()

    private val moviesID = moviesRemote[0].id
    private val tvShowsID = tvShowsRemote[0].id

    private val moviesDetailsResponse = DataDetail.getMoviesDetailsRemote()
    private val tvShowsDetailsResponse = DataDetail.getTvShowsDetailsRemote()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getMoviesList() {
        Mockito.mock(DataSource.Factory::class.java).also {
            Mockito.`when`(localDataSource.getMovies()).thenReturn(it as DataSource.Factory<Int, MovieEntity>)
            fakeRepository.getMovie()
        }

        Resource.success(PagedListUtils.mockingPagedList(Data.getMovies())).also {
            verify(localDataSource).getMovies()

            with(it.data) {
                assertThat(this).isNotNull()
                assertThat(this?.size?.toLong()).isEqualTo(moviesRemote.size.toLong())
            }
        }
    }

    @Test
    fun getTvShowsList() {
        Mockito.mock(DataSource.Factory::class.java).also {
            Mockito.`when`(localDataSource . getTvShows ()
            ).thenReturn(it as DataSource.Factory<Int, TvShowsEntity>)
            fakeRepository.getTvShows()
        }

        Resource.success(PagedListUtils.mockingPagedList(Data.getTvShows())).also {
            verify(localDataSource).getTvShows()

            with(it.data) {
                assertThat(this).isNotNull()
                assertThat(this?.size?.toLong()).isEqualTo(tvShowsRemote.size.toLong())
            }
        }
    }

    @Test
    fun getMoviesDetails() {
        MutableLiveData<MovieEntity>().also {
            it.value = DataDetail.getMoviesDetails()
            Mockito.`when`(localDataSource.getMoviesByID(moviesID)).thenReturn(it)
        }

        LiveDataTest.getValue(fakeRepository.getMovieDetail(moviesID)).also {
            verify(localDataSource).getMoviesByID(moviesID)

            assertThat(it).isNotNull()
            assertThat(it.data?.id).isEqualTo(moviesDetailsResponse.id)
        }
    }

    @Test
    fun getTvShowsDetails() {
        MutableLiveData<TvShowsEntity>().also {
            it.value = DataDetail.getTvShowsDetails()
            Mockito.`when`(localDataSource.getTvShowsByID(tvShowsID)).thenReturn(it)
        }

        LiveDataTest.getValue(fakeRepository.getTvShowsDetail(tvShowsID)).also {
            verify(localDataSource).getTvShowsByID(tvShowsID)

            assertThat(it).isNotNull()
            assertThat(it.data?.id).isEqualTo(tvShowsDetailsResponse.id)
        }
    }

    @Test
    fun setFavoriteMovie() {
        fakeRepository.setFavoriteMovie(DataDetail.getMoviesDetails(), true)

        verify(localDataSource).updateMoviesWatchlist(DataDetail.getMoviesDetails(), true)
        verifyNoMoreInteractions(localDataSource)
    }

    @Test
    fun getFavoriteMovie() {
        Mockito.mock(DataSource.Factory::class.java).also {
            Mockito.`when`(localDataSource.getMoviesWatchList()).thenReturn(it as DataSource.Factory<Int, MovieEntity>)
            fakeRepository.getFavoriteMovie()
        }

        Resource.success(PagedListUtils.mockingPagedList(Data.getMovies())).also {
            verify(localDataSource).getMoviesWatchList()

            assertThat(it).isNotNull()
            assertThat(it.data?.size).isEqualTo(moviesRemote.size)
        }
    }
    @Test
    fun setFavoriteTv() {
        fakeRepository.setFavoriteTvShows(DataDetail.getTvShowsDetails(), true)

        verify(localDataSource).updateTvShowsWatchlist(DataDetail.getTvShowsDetails(), true)
        verifyNoMoreInteractions(localDataSource)
    }

    @Test
    fun getFavoriteTv() {
        Mockito.mock(DataSource.Factory::class.java).also {
            Mockito.`when`(localDataSource.getTvShowsWatchList()).thenReturn(it as DataSource.Factory<Int, TvShowsEntity>)
            fakeRepository.getFavoriteTvShows()
        }

        Resource.success(PagedListUtils.mockingPagedList(Data.getTvShows())).also {
            verify(localDataSource).getTvShowsWatchList()

            assertThat(it).isNotNull()
            assertThat(it.data?.size).isEqualTo(tvShowsRemote.size)
        }
    }
}