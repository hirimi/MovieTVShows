package com.example.sub33.di

import android.content.Context
import com.example.sub33.remote.respose.LocalDataSource
import com.example.sub33.remote.respose.Repository
import com.example.sub33.room.FilmDatabase
import com.example.sub33.source.RemoteDataSource
import com.example.sub33.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getRemoteDataSource()
        val localDataSource = LocalDataSource.getLocalDataSource(database.filmDao())
        val appExecutors = AppExecutors()
        return Repository.getRepository(remoteDataSource, localDataSource, appExecutors)
    }
}