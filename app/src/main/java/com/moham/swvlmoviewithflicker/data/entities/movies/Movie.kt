package com.moham.swvlmoviewithflicker.data.entities.movies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var title: String = "",
    var year: Int = 0,
    var cast: List<String> = listOf(),
    var genres: List<String> = listOf(),
    var rating: Int = 0,
):Parcelable