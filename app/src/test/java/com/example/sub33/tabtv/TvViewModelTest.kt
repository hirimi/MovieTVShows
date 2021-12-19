package com.example.sub33.tabtv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.remote.respose.Repository
import com.example.sub33.vo.Resource
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {
    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowsEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowsEntity>

    @Before
    fun setUp() {
        viewModel = TvViewModel(repository)
    }

    @Test
    fun tvShows() {
        val resource = Resource.success(pagedList)

        Mockito.`when`(resource.data?.size).thenReturn(3)

        MutableLiveData<Resource<PagedList<TvShowsEntity>>>().also {
            it.value = resource
            Mockito.`when`(repository.getTvShows()).thenReturn(it)
        }

        viewModel.getTvShows().apply {
            value?.data.also {
                com.nhaarman.mockitokotlin2.verify(repository).getTvShows()
                assertThat(it).isNotNull()
                assertThat(it?.size).isEqualTo(3)
            }

            observeForever(observer)
            com.nhaarman.mockitokotlin2.verify(observer).onChanged(resource)
        }
    }
}