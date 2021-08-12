package com.moham.swvlmoviewithflicker.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.moham.swvlmoviewithflicker.data.entities.flickrPhoto.FlickrPhoto
import com.moham.swvlmoviewithflicker.data.repository.MovieRepository
import com.moham.swvlmoviewithflicker.domain.GetPhotosUseCase
import com.moham.swvlmoviewithflicker.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) :
    ViewModel() {

    private val params = MutableLiveData<String>()

    private val movie = params.switchMap { id ->
        getPhotosUseCase(id)
    }
    val movieLiveData: LiveData<Resource<FlickrPhoto>> = movie

    fun getPhotos(params: String) {
        this.params.value = params
    }

}
