package com.example.sub33.tabmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.sub33.entity.MovieEntity
import com.example.sub33.remote.respose.Repository
import com.example.sub33.vo.Resource
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun movie() {
        val resource = Resource.success(pagedList)

        Mockito.`when`(resource.data?.size).thenReturn(3)

        MutableLiveData<Resource<PagedList<MovieEntity>>>().also {
            it.value = resource
            Mockito.`when`(repository.getMovie()).thenReturn(it)
        }

        viewModel.getMovie().apply {
            value?.data.also {
                verify(repository).getMovie()
                assertThat(it).isNotNull()
                assertThat(it?.size).isEqualTo(3)
            }

            observeForever(observer)
            verify(observer).onChanged(resource)
        }
    }
}
