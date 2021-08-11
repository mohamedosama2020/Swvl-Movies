package com.moham.swvlmoviewithflicker.domain

import android.content.Context
import com.moham.swvlmoviewithflicker.data.entities.movies.MoviesDetail
import com.google.gson.Gson
import com.moham.swvlmoviewithflicker.utils.loadJSONFromAsset
import javax.inject.Inject


class GetMoviesUseCase @Inject constructor(val context: Context) {

    operator fun invoke(): MoviesDetail {
        //Load Json From Android Asset Then Parse Json To Model Object (MoviesDetail)
        val dataString = loadJSONFromAsset(context, "movies.json")
        return Gson().fromJson(dataString, MoviesDetail::class.java)
    }
}