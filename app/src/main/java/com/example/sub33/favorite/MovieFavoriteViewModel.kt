package com.example.sub33.favorite

import androidx.lifecycle.ViewModel
import com.example.sub33.entity.MovieEntity
import com.example.sub33.remote.respose.Repository

class MovieFavoriteViewModel(private val repository: Repository) : ViewModel() {
    fun getFavMovies() = repository.getFavoriteMovie()

    fun setFavMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.movielist
        repository.setFavoriteMovie(movieEntity, newState)
    }
}