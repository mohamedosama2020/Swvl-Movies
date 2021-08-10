package com.moham.swvlmoviewithflicker.data.remote

import com.moham.swvlmoviewithflicker.data.entities.flickrPhoto.FlickrPhoto
import retrofit2.Response
import retrofit2.http.*

interface MovieService {

    @GET("services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun getFlickrPhotoData(@QueryMap params: Map<String, String>): Response<FlickrPhoto>


}