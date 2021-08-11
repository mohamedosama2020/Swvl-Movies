package com.moham.swvlmoviewithflicker.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moham.swvlmoviewithflicker.data.entities.movies.MoviesDetail
import com.moham.swvlmoviewithflicker.data.entities.movies.GroupedMovies
import com.moham.swvlmoviewithflicker.data.entities.movies.Movie
import com.moham.swvlmoviewithflicker.domain.GetFilteredMoviesUseCase
import com.moham.swvlmoviewithflicker.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesUseCase,
    private val getFilteredMoviesUseCase: GetFilteredMoviesUseCase
) :
    ViewModel() {
    val searchQuery = MutableLiveData<String>()

    fun setSearchQuery(query: String) {
        this.searchQuery.value = query
    }

    fun loadMoviesList(): MoviesDetail {
        return getMoviesListUseCase()
    }

    fun getFilteredMovies(searchQuery:String,movies:List<Movie>): List<GroupedMovies> {
       return  getFilteredMoviesUseCase.invoke(searchQuery,movies)
    }
}
