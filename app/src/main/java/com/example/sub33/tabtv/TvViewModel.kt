package com.example.sub33.tabtv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.remote.respose.Repository
import com.example.sub33.vo.Resource

class TvViewModel(private val repository: Repository) : ViewModel() {
    fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> = repository.getTvShows()
}