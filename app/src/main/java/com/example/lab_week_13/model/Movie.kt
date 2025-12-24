package com.example.lab_week_13.model

import androidx.room.Entity

@Entity(tableName = "movies", primaryKeys = ["id"])
data class Movie(
    val id: Int,
    val title: String,
    val popularity: Double,
    val release_date: String?,
    val backdrop_path: String?
)
