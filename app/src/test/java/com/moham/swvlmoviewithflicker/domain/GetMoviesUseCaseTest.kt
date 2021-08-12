package com.moham.swvlmoviewithflicker.domain

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.moham.swvlmoviewithflicker.MainApplication
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = 30,minSdk = 24)
class GetMoviesUseCaseTest : TestCase() {

    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun setup(){
        val app = getApplicationContext<MainApplication>()
        getMoviesUseCase = GetMoviesUseCase(app)
    }

    @Test
    fun `test_getMoviesUseCase_with valid json name_then return not empty movie value`() {
        val movieDetail = getMoviesUseCase.invoke(jsonFile = "movies.json")
        assertTrue(movieDetail.movies.isNotEmpty(),"There is Movies")
    }

    @Test
    fun `test_getMoviesUseCase_with wrong json name_then return empty movies value`() {
        val movieDetail = getMoviesUseCase.invoke(jsonFile = "moviees.json")
        assertTrue(movieDetail.movies.isEmpty(),"There is No Movies")
    }
}