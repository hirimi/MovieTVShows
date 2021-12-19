package com.example.sub33.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sub33.remote.respose.ApiResponse
import com.example.sub33.remote.respose.movie.MovieDetailResponse
import com.example.sub33.remote.respose.movie.MovieRemote
import com.example.sub33.remote.respose.movie.MovieResponse
import com.example.sub33.remote.respose.tv.TvShowsDetailResponse
import com.example.sub33.remote.respose.tv.TvShowsRemote
import com.example.sub33.remote.respose.tv.TvShowsResponse
import com.example.sub33.retrofit.ApiClient
import com.example.sub33.utils.EspressoIdlingResource.decrement
import com.example.sub33.utils.EspressoIdlingResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun getMoviesList(): LiveData<ApiResponse<List<MovieRemote>>> {
        increment()

        MutableLiveData<ApiResponse<List<MovieRemote>>>().also {
            ApiClient.getService().getMoviesList(1).enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    it.value = ApiResponse.success(response.body()?.result as List<MovieRemote>)

                    decrement()
                }

                override fun onFailure(call: Call<MovieResponse>, throwable: Throwable) {
                    Log.e(TAG, "Failure ${throwable.message}")

                    decrement()
                }
            })

            return it
        }
    }

    fun getTvShowsList(): LiveData<ApiResponse<List<TvShowsRemote>>> {
        increment()

        MutableLiveData<ApiResponse<List<TvShowsRemote>>>().also {
            ApiClient.getService().getTvShowsList(1).enqueue(object : Callback<TvShowsResponse> {
                override fun onResponse(call: Call<TvShowsResponse>, response: Response<TvShowsResponse>) {
                    it.value = ApiResponse.success(response.body()?.result as List<TvShowsRemote>)

                    decrement()
                }

                override fun onFailure(call: Call<TvShowsResponse>, throwable: Throwable) {
                    Log.e(TAG, "Failure ${throwable.message}")

                    decrement()
                }
            })

            return it
        }
    }

    fun getMoviesDetails(moviesID: String): LiveData<ApiResponse<MovieDetailResponse>> {
        increment()

        MutableLiveData<ApiResponse<MovieDetailResponse>>().also {
            ApiClient.getService().getMoviesDetails(moviesID).enqueue(object :
                Callback<MovieDetailResponse> {
                override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                    it.value = ApiResponse.success(response.body() as MovieDetailResponse)

                    decrement()
                }

                override fun onFailure(call: Call<MovieDetailResponse>, throwable: Throwable) {
                    Log.e(TAG, "Failure ${throwable.message}")

                    decrement()
                }
            })

            return it
        }
    }

    fun getTvShowsDetails(tvShowsID: String): LiveData<ApiResponse<TvShowsDetailResponse>> {
        increment()

        MutableLiveData<ApiResponse<TvShowsDetailResponse>>().also {
            ApiClient.getService().getTvShowsDetails(tvShowsID).enqueue(object :
                Callback<TvShowsDetailResponse> {
                override fun onResponse(call: Call<TvShowsDetailResponse>, response: Response<TvShowsDetailResponse>) {
                    it.value = ApiResponse.success(response.body() as TvShowsDetailResponse)

                    decrement()
                }

                override fun onFailure(call: Call<TvShowsDetailResponse>, throwable: Throwable) {
                    Log.e(TAG, "Failure ${throwable.message}")

                    decrement()
                }
            })

            return it
        }
    }

    companion object {
        const val TAG = "RemoteDataSource"

        @Volatile
        private var remoteDataSource: RemoteDataSource? = null

        fun getRemoteDataSource(): RemoteDataSource {
            return remoteDataSource ?: synchronized(this) {
                RemoteDataSource().apply {
                    remoteDataSource = this
                }
            }
        }
    }
}