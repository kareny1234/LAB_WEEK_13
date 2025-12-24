package com.example.lab_week_13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_week_13.adapter.MovieAdapter
import com.example.lab_week_13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = MovieAdapter()
        binding.movieList.layoutManager = LinearLayoutManager(this)
        binding.movieList.adapter = adapter

        val viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MovieViewModel(
                        (application as MovieApplication).repository
                    ) as T
                }
            }
        )[MovieViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
