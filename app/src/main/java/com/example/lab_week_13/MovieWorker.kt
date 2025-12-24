package com.example.lab_week_13

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.runBlocking

class MovieWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val repo =
            (applicationContext as MovieApplication).movieRepository

        runBlocking {
            repo.refreshMovies()
        }

        return Result.success()
    }
}
