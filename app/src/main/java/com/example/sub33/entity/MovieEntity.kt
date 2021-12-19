package com.example.sub33.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
data class MovieEntity(
    @PrimaryKey @NonNull @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "poster")
    val poster: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,
    @ColumnInfo(name = "synopsis")
    val synopsis: String,
    @ColumnInfo(name = "watchlist")
    var movielist: Boolean = false
)