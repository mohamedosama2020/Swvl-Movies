package com.moham.swvlmoviewithflicker.domain

import com.moham.swvlmoviewithflicker.data.entities.movies.GroupedMovies
import com.moham.swvlmoviewithflicker.data.entities.movies.Movie
import javax.inject.Inject


class GetFilteredMoviesUseCase @Inject constructor() {

    operator fun invoke(searchQuery: String, movies: List<Movie>): List<GroupedMovies> {
        //Get Movies By Title
        val searchedMovie = movies.filter { it.title.lowercase().contains(searchQuery.lowercase()) }
        //Group Searched Movies By Year
        val searchedMoviesGroupedByYear = searchedMovie.groupBy { it.year }
        //Map Grouped Movies To New Model (Grouped Movies)
        //Then Sort by Rating And Get First 5 If Available
        //And Return The Final Result => List Of (Grouped Movies)
        val groupedMovies = searchedMoviesGroupedByYear.map { movieHashMap ->
            val sortedMoviesByRating = movieHashMap.value.sortedByDescending { it.rating }
            val firstFiveValues = sortedMoviesByRating.take(5)
            GroupedMovies(movieHashMap.key, firstFiveValues)
        }
        return groupedMovies.sortedBy { it.year }

    }
}