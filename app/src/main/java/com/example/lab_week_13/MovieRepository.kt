package com.example.lab_week_13

import com.example.lab_week_13.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(
    private val service: MovieService
) {
    private val apiKey = "ISI_API_KEY_TMDB_KAMU"

    fun fetchMovies(): Flow<List<Movie>> = flow {
        val movies = service.getPopularMovies(apiKey).results
        emit(movies.sortedByDescending { it.popularity })
    }.flowOn(Dispatchers.IO)
}
