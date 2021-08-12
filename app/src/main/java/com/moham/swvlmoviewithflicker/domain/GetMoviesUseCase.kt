package com.moham.swvlmoviewithflicker.domain

import android.content.Context
import com.moham.swvlmoviewithflicker.data.entities.movies.MoviesDetail
import com.google.gson.Gson
import com.moham.swvlmoviewithflicker.data.entities.movies.Movie
import com.moham.swvlmoviewithflicker.utils.loadJSONFromAsset
import java.io.FileNotFoundException
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject


class GetMoviesUseCase @Inject constructor(val context: Context) {

    private lateinit var moviesDetail:MoviesDetail

    operator fun invoke(jsonFile:String = "movies.json"): MoviesDetail {
        //Load Json From Android Asset Then Parse Json To Model Object (MoviesDetail)
        moviesDetail = try{
            val dataString = loadJSONFromAsset(context, jsonFile)
            Gson().fromJson(dataString, MoviesDetail::class.java)
        }catch (e:NullPointerException){
            MoviesDetail()
        }
        return moviesDetail
    }
}