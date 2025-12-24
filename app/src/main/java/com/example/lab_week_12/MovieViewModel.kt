package com.example.lab_week_12

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.fetchMovies()
                    .collect { movies: List<Movie> ->
                        // ✅ ASSIGNMENT: SORT DESCENDING BY POPULARITY
                        _popularMovies.value =
                            movies.sortedByDescending { it.popularity }
                    }
            } catch (e: Exception) {
                _error.value = e.message ?: "Error occurred"
            }
        }
    }
}
