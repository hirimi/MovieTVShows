package com.example.sub33.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.sub33.entity.MovieEntity
import com.example.sub33.remote.respose.Repository
import com.example.sub33.utils.DataDetail
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {
    private lateinit var viewModel: MovieFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavoriteViewModel(repository)
    }

    @Test
    fun getFavMovies() {
        val moviePagedList = pagedList
        Mockito.`when`(moviePagedList.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = moviePagedList

        Mockito.`when`(repository.getFavoriteMovie()).thenReturn(movies)
        val movie = viewModel.getFavMovies().value
        verify(repository).getFavoriteMovie()
        Assert.assertNotNull(movie)
        Assert.assertEquals(3, movie?.size)

        viewModel.getFavMovies().observeForever(observer)
        verify(observer).onChanged(moviePagedList)
    }

    @Test
    fun setFavMovie() {
        viewModel.setFavMovie(DataDetail.getMoviesDetails())
        verify(repository).setFavoriteMovie(DataDetail.getMoviesDetails(), true)
        verifyNoMoreInteractions(repository)
    }
}