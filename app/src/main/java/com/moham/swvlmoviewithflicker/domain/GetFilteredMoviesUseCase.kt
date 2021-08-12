package com.moham.swvlmoviewithflicker.domain

import com.moham.swvlmoviewithflicker.data.entities.movies.GroupedMovies
import com.moham.swvlmoviewithflicker.data.entities.movies.Movie
import javax.inject.Inject


class GetFilteredMoviesUseCase @Inject constructor() {

    operator fun invoke(searchQuery: String, movies: List<Movie>): List<GroupedMovies> {
        //Search Movies By Title
        val searchedMovies = searchMoviesByTitle(searchQuery, movies)
        //Group Searched Movies By Year
        val searchedMoviesGroupedByYear = categorizeSearchedMovieByYear(searchedMovies)
        //Map Grouped Movies To New Model (Grouped Movies)
        //Then Sort by Rating And Get First 5 If Available
        //And Return The Final Result => List Of (Grouped Movies)
        return getGroupedMoviesWithTopRated(searchedMoviesGroupedByYear)
    }

    fun getGroupedMoviesWithTopRated(searchedMoviesGroupedByYear: Map<Int, List<Movie>>): List<GroupedMovies> {
        val groupedMovies = searchedMoviesGroupedByYear.map { movieHashMap ->
            val sortedMoviesByRating = movieHashMap.value.sortedByDescending { it.rating }
            val firstFiveValues = sortedMoviesByRating.take(5)
            GroupedMovies(movieHashMap.key, firstFiveValues)
        }
        return groupedMovies.sortedBy { it.year }
    }

    fun searchMoviesByTitle(searchQuery: String, movies: List<Movie>) =
        movies.filter { it.title.lowercase().contains(searchQuery.lowercase()) }


    fun categorizeSearchedMovieByYear(searchedMovies: List<Movie>) =
        searchedMovies.groupBy { it.year }

}