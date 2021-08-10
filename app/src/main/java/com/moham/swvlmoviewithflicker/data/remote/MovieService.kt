package com.moham.swvlmoviewithflicker.data.remote

import com.moham.swvlmoviewithflicker.BuildConfig
import com.moham.swvlmoviewithflicker.data.entities.flickrPhoto.FlickrPhoto
import retrofit2.Response
import retrofit2.http.*

interface MovieService {

    @GET("services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun getFlickrPhotoData(
        @Query("text") params: String,
        @Query("api_key") apiKey: String = BuildConfig.FLICKER_KEY,
        @Query("page") page: String = "1",
        @Query("per_page") per_page: String = "10",
    ): Response<FlickrPhoto>


}