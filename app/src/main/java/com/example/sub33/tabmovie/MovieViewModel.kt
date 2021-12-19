package com.example.sub33.tabmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.sub33.entity.MovieEntity
import com.example.sub33.remote.respose.Repository
import com.example.sub33.vo.Resource

class MovieViewModel(private val repository: Repository) : ViewModel() {

    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> = repository.getMovie()
}