package com.example.sub33.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.sub33.entity.TvShowsEntity
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
class TvFavoriteViewModelTest {
    private lateinit var viewModel: TvFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowsEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowsEntity>

    @Before
    fun setUp() {
        viewModel = TvFavoriteViewModel(repository)
    }

    @Test
    fun getFavTvShows() {
        val tvShowsPagedlist = pagedList
        Mockito.`when`(tvShowsPagedlist.size).thenReturn(3)
        val tvShows = MutableLiveData<PagedList<TvShowsEntity>>()
        tvShows.value = tvShowsPagedlist

        Mockito.`when`(repository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getFavTvShows().value
        verify(repository).getFavoriteTvShows()
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(3, tvShow?.size)

        viewModel.getFavTvShows().observeForever(observer)
        verify(observer).onChanged(tvShowsPagedlist)
    }

    @Test
    fun setFavTv() {
        viewModel.setFavTvShow(DataDetail.getTvShowsDetails())
        verify(repository).setFavoriteTvShows(DataDetail.getTvShowsDetails(), true)
        verifyNoMoreInteractions(repository)
    }
}