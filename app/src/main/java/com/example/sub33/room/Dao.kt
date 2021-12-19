package com.example.sub33.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.Dao
import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity

@Dao
interface Dao {

    @Query("SELECT * FROM movie_entity")
    fun movies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_shows_entity")
    fun tvShows(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM movie_entity WHERE watchlist = 1")
    fun moviesWatchlist(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tv_shows_entity WHERE watchlist = 1")
    fun tvShowsWatchlist(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM movie_entity WHERE id = :id")
    fun moviesID(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tv_shows_entity WHERE id = :id")
    fun tvShowsID(id: Int): LiveData<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowsEntity>)

    @Update
    fun updateMovies(movies: MovieEntity)

    @Update
    fun updateTvShows(tvShows: TvShowsEntity)

}