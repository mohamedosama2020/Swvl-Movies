package com.moham.swvlmoviewithflicker.data.remote

import com.moham.swvlmoviewithflicker.data.remote.BaseDataSource
import com.moham.swvlmoviewithflicker.data.remote.MovieService
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService): BaseDataSource() {

    suspend fun getFlickrPhotoData(params: String) = getResult { movieService.getFlickrPhotoData(params) }
}