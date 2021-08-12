package com.moham.swvlmoviewithflicker.domain

import androidx.test.core.app.ApplicationProvider
import com.moham.swvlmoviewithflicker.MainApplication
import com.moham.swvlmoviewithflicker.data.entities.movies.MoviesDetail
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = 30, minSdk = 24)
class GetFilteredMoviesUseCaseTest {

    private lateinit var moviesObject: MoviesDetail
    private lateinit var filterMoviesUseCase: GetFilteredMoviesUseCase

    @Before
    fun setup() {
        val app = ApplicationProvider.getApplicationContext<MainApplication>()
        moviesObject = GetMoviesUseCase(app).invoke()
        filterMoviesUseCase = GetFilteredMoviesUseCase()
    }

    @Test
    fun `test_filterMoviesUseCase_with searchQuery_then return listOf movies contains that query`() {
        val searchQuery = "day"
        val moviesList = filterMoviesUseCase.invoke(searchQuery, moviesObject.movies)
        val randomIndex = moviesList.indices.random()
        val isMovieMatchesSearch =
            moviesList[randomIndex].movies[0].title.contains(searchQuery, true)
        Assertions.assertTrue(isMovieMatchesSearch, "Movie Not Matches Search Query")

    }

    @Test
    fun `test_filterMoviesUseCase_with searchQuery_then return movies no more than five entries per year`() {
        val searchQuery = "hou"
        val moviesList = filterMoviesUseCase.invoke(searchQuery, moviesObject.movies)
        var isEntryNotGreaterThan5 = true
        moviesList.forEach {
            if (it.movies.size > 5) {
                isEntryNotGreaterThan5 = false
                return@forEach
            }
        }
        Assertions.assertTrue(isEntryNotGreaterThan5, "Movies Entry Must Not Be Greater Than 5")
    }

    @Test
    fun `test_filterMoviesUseCase_with searchQuery_then return movies with top 5 rated in Year`() {
        val searchQuery = "hou"
        val searchedMovies = filterMoviesUseCase.searchMoviesByTitle(searchQuery,moviesObject.movies)
        val moviesByYear = filterMoviesUseCase.categorizeSearchedMovieByYear(searchedMovies)
        val titleOfTopMovie2013 = "House of Dust"
        val top5RatedMovies = filterMoviesUseCase.getGroupedMoviesWithTopRated(moviesByYear)
        val isMoviesTopRated = top5RatedMovies.find { it.year == 2013 }
        ?.movies?.get(0)?.title == titleOfTopMovie2013

        Assertions.assertTrue(isMoviesTopRated, "Top Rated Movie In 2013 Must Be House Of Dust")
    }
}