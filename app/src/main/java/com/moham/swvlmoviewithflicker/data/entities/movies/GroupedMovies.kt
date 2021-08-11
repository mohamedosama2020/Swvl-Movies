package com.moham.swvlmoviewithflicker.data.entities.movies

data class GroupedMovies(
    var year: Int? = null,
    var movies: List<Movie> = listOf()
)