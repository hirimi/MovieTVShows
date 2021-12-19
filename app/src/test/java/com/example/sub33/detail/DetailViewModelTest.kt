package com.example.sub33.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sub33.detail.DetailViewModel.Companion.TV_SHOWS
import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.remote.respose.Repository
import com.example.sub33.utils.DataDetail
import com.example.sub33.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val movieDetail = DataDetail.getMoviesDetails()
    private val tvShowsDetail = DataDetail.getTvShowsDetails()

    private val moviesID = movieDetail.id
    private val tvShowsID = tvShowsDetail.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var moviesObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowsObserver: Observer<Resource<TvShowsEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun getMoviesDetails() {
        MutableLiveData<Resource<MovieEntity>>().also {
            it.value = Resource.success(DataDetail.getMoviesDetails())
            Mockito.`when`(repository.getMovieDetail(moviesID)).thenReturn(it)

            viewModel.setAll(moviesID.toString(), DetailViewModel.MOVIES)
            val movieData = viewModel.getDetailMovie().value
            Assert.assertNotNull(movieData)
            TestCase.assertEquals(movieDetail.id, movieData?.data?.id)
            TestCase.assertEquals(movieDetail.poster, movieData?.data?.poster)
            TestCase.assertEquals(movieDetail.title, movieData?.data?.title)
            TestCase.assertEquals(movieDetail.rating, movieData?.data?.rating)
            TestCase.assertEquals(movieDetail.releaseDate, movieData?.data?.releaseDate)
            TestCase.assertEquals(movieDetail.synopsis, movieData?.data?.synopsis)
            viewModel.getDetailMovie().observeForever(moviesObserver)
            verify(moviesObserver).onChanged(it.value)
        }
    }

    @Test
    fun getTvShowsDetails() {
        MutableLiveData<Resource<TvShowsEntity>>().also {
            it.value = Resource.success(DataDetail.getTvShowsDetails())
            Mockito.`when`(repository.getTvShowsDetail(tvShowsID)).thenReturn(it)

            viewModel.setAll(tvShowsID.toString(), TV_SHOWS)
            val tvData = viewModel.getDetailTvShow().value
            Assert.assertNotNull(tvData)
            TestCase.assertEquals(tvShowsDetail.id, tvData?.data?.id)
            TestCase.assertEquals(tvShowsDetail.poster, tvData?.data?.poster)
            TestCase.assertEquals(tvShowsDetail.title, tvData?.data?.title)
            TestCase.assertEquals(tvShowsDetail.rating, tvData?.data?.rating)
            TestCase.assertEquals(tvShowsDetail.releaseDate, tvData?.data?.releaseDate)
            TestCase.assertEquals(tvShowsDetail.synopsis, tvData?.data?.synopsis)
            viewModel.getDetailTvShow().observeForever(tvShowsObserver)
            verify(tvShowsObserver).onChanged(it.value)
        }
    }

    @Test
    fun setFavoriteMovie() {
        val detailMovie = Resource.success(DataDetail.getMoviesDetails())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = detailMovie

        Mockito.`when`(repository.getMovieDetail(moviesID)).thenReturn(movie)

        viewModel.setAll(moviesID.toString(), DetailViewModel.MOVIES)
        viewModel.setFavoriteMovie()
        verify(repository).setFavoriteMovie(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(moviesObserver)
    }

    @Test
    fun setFavoriteTvShow() {
        val detailTvShow = Resource.success(DataDetail.getTvShowsDetails())
        val tvShow = MutableLiveData<Resource<TvShowsEntity>>()
        tvShow.value = detailTvShow

        Mockito.`when`(repository.getTvShowsDetail(tvShowsID)).thenReturn(tvShow)

        viewModel.setAll(tvShowsID.toString(), TV_SHOWS)
        viewModel.setFavoriteTvShow()
        verify(repository).setFavoriteTvShows(tvShow.value!!.data as TvShowsEntity, true)
        verifyNoMoreInteractions(tvShowsObserver)
    }
}