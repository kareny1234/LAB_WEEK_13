package com.example.lab_week_13

import com.example.lab_week_13.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "PASTE_API_KEY_TMDB_KAMU"

    fun fetchMovies(): Flow<List<Movie>> = flow {
        val response = movieService.getPopularMovies(apiKey)
        emit(response.results)
    }.flowOn(Dispatchers.IO)
}
