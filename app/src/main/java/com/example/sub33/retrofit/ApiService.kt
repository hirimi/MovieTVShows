package com.example.sub33.retrofit

import com.example.sub33.BuildConfig.API_KEY
import com.example.sub33.remote.respose.movie.MovieDetailResponse
import com.example.sub33.remote.respose.movie.MovieResponse
import com.example.sub33.remote.respose.tv.TvShowsDetailResponse
import com.example.sub33.remote.respose.tv.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular?api_key=$API_KEY")
    fun getMoviesList(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getMoviesDetails(@Path("movie_id") id: String): Call<MovieDetailResponse>

    @GET("tv/popular?api_key=$API_KEY")
    fun getTvShowsList(@Query("page") page: Int): Call<TvShowsResponse>

    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getTvShowsDetails(@Path("tv_id") id: String): Call<TvShowsDetailResponse>

}