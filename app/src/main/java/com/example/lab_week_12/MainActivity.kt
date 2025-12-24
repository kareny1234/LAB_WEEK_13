package com.example.lab_week_12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.movie_list)

        recyclerView.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)

        val movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter


        val repository =
            (application as MovieApplication).movieRepository

        val viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MovieViewModel(repository) as T
                }
            }
        )[MovieViewModel::class.java]

        viewModel.popularMovies.observe(this) {
            movieAdapter.addMovies(it)
        }

        viewModel.error.observe(this) {
            if (it.isNotEmpty())
                Snackbar.make(recyclerView, it, Snackbar.LENGTH_LONG).show()
        }
    }
}
