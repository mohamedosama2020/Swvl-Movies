package com.moham.swvlmoviewithflicker.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moham.swvlmoviewithflicker.data.remote.MovieRemoteDataSource
import com.moham.swvlmoviewithflicker.data.remote.MovieService
import com.moham.swvlmoviewithflicker.domain.GetFilteredMoviesUseCase
import com.moham.swvlmoviewithflicker.domain.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Singleton
    @Provides
    fun provideMoviesUseCase(context: Context): GetMoviesUseCase = GetMoviesUseCase(context)

    @Singleton
    @Provides
    fun provideFilteredMoviesUseCase(): GetFilteredMoviesUseCase = GetFilteredMoviesUseCase()
}