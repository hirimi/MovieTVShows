package com.example.sub33.remote.respose.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val result: List<MovieRemote>
)