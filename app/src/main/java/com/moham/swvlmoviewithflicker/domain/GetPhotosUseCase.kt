package com.moham.swvlmoviewithflicker.domain

import com.moham.swvlmoviewithflicker.data.repository.MovieRepository
import javax.inject.Inject


class GetPhotosUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(id:String) = repository.getFlickrPhotoData(id)
}