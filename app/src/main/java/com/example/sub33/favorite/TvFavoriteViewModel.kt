package com.example.sub33.favorite

import androidx.lifecycle.ViewModel
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.remote.respose.Repository

class TvFavoriteViewModel(private val repository: Repository) : ViewModel() {
    fun getFavTvShows() = repository.getFavoriteTvShows()

    fun setFavTvShow(tvEntity: TvShowsEntity) {
        val newState = !tvEntity.tvlist
        repository.setFavoriteTvShows(tvEntity, newState)
    }
}