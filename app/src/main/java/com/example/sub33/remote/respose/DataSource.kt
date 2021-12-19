package com.example.sub33.remote.respose

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.vo.Resource

interface DataSource {
    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>>

    fun getMovieDetail(moviesID: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowsDetail(tvShowsID: Int): LiveData<Resource<TvShowsEntity>>

    fun setFavoriteMovie(moviesEntity: MovieEntity, state: Boolean)

    fun setFavoriteTvShows(tvShowsEntity: TvShowsEntity, state: Boolean)

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowsEntity>>

}