package com.moham.swvlmoviewithflicker.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.entities.movies.MoviesDetail
import com.moham.swvlmoviewithflicker.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesListUseCase:GetMoviesUseCase) : ViewModel() {
    val searchQuery = MutableLiveData<String>()

    fun setSearchQuery(query: String) {
        this.searchQuery.value = query
    }

    fun loadMoviesList(): MoviesDetail {
        return getMoviesListUseCase()
    }
}
