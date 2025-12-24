package com.example.lab_week_12

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lab_week_12.model.Movie

class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "ISI_API_KEY_KAMU_DI_SINI"

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    suspend fun fetchMovies() {
        try {
            val response = movieService.getPopularMovies(apiKey)
            _movies.postValue(response.results)
        } catch (e: Exception) {
            _error.postValue(e.message ?: "Unknown error")
        }
    }
}
