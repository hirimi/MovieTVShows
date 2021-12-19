package com.example.sub33.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.remote.respose.Repository
import com.example.sub33.vo.Resource

class DetailViewModel(private val repository: Repository) : ViewModel() {

    private lateinit var detailTv: LiveData<Resource<TvShowsEntity>>
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun setAll(id: String, category: String) {
        when (category) {
            TV_SHOWS -> {
                detailTv = repository.getTvShowsDetail(id.toInt())
            }

            MOVIES -> {
                detailMovie = repository.getMovieDetail(id.toInt())
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.movielist
            repository.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTv.value
        if (resource?.data != null) {
            val newState = !resource.data.tvlist
            repository.setFavoriteTvShows(resource.data, newState)
        }
    }

    fun getDetailTvShow() = detailTv
    fun getDetailMovie() = detailMovie

    companion object {
        const val MOVIES = "movies_details"
        const val TV_SHOWS = "tv_shows_details"
    }
}