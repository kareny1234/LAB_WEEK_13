package com.example.lab_week_13.database

import android.content.Context
import androidx.room.*
import com.example.lab_week_13.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile private var INSTANCE: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context, MovieDatabase::class.java, "movie-db"
                ).build().also { INSTANCE = it }
            }
    }
}
