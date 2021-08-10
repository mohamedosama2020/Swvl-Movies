package com.moham.swvlmoviewithflicker.data.repository

import com.moham.swvlmoviewithflicker.data.remote.MovieRemoteDataSource
import com.example.movies.utils.performGetOperation
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) {


    fun getFlickrPhotoData(params: String) =
        performGetOperation(networkCall = { remoteDataSource.getFlickrPhotoData(params) })


}