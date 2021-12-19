package com.example.sub33.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity

@Database(
    entities = [MovieEntity::class, TvShowsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: FilmDatabase? = null

        fun getInstance(context: Context): FilmDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: Room.databaseBuilder(context.applicationContext, FilmDatabase::class.java, "Film.db").build()
            }
    }
}