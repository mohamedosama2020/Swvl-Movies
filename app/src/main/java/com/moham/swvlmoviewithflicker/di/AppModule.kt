package com.moham.swvlmoviewithflicker.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moham.swvlmoviewithflicker.data.remote.MovieRemoteDataSource
import com.moham.swvlmoviewithflicker.data.remote.MovieService
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
object AppModule {


   

    @Singleton
    @Provides
    fun provideRetrofit(context: Context, gson: Gson,cacheInterceptor:Interceptor): Retrofit {


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheSize = (10 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .cache(myCache)
            .addInterceptor(cacheInterceptor)
            .addInterceptor(interceptor).build()


        return Retrofit.Builder()
            .baseUrl("https://www.flickr.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(
        MovieService::class.java
    )

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(movieService: MovieService) =
        MovieRemoteDataSource(movieService)
}