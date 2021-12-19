package com.example.sub33.remote.respose.tv

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    @SerializedName("results")
    val result: List<TvShowsRemote>
)