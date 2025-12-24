package com.example.lab_week_13

import android.app.Application
import androidx.work.*
import com.example.lab_week_13.database.MovieDatabase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MovieApplication : Application() {

    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(MovieService::class.java)
        val database = MovieDatabase.getInstance(this)

        movieRepository = MovieRepository(service, database)

        val workRequest = PeriodicWorkRequestBuilder<MovieWorker>(
            1, TimeUnit.HOURS
        ).setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        ).build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}
