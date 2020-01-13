package com.id.zul.playit.model.movie

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double
)