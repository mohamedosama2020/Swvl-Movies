package com.moham.swvlmoviewithflicker.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor() : ViewModel() {
    val searchQuery = MutableLiveData<String>()
    fun setSearchQuery(query: String) {
        this.searchQuery.value = query
    }
}
