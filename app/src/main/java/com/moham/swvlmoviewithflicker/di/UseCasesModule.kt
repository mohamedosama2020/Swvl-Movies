package com.moham.swvlmoviewithflicker.di

import android.content.Context
import com.moham.swvlmoviewithflicker.data.repository.MovieRepository
import com.moham.swvlmoviewithflicker.domain.GetFilteredMoviesUseCase
import com.moham.swvlmoviewithflicker.domain.GetMoviesUseCase
import com.moham.swvlmoviewithflicker.domain.GetPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

    @Singleton
    @Provides
    fun provideGetPhotosUseCase(repo:MovieRepository): GetPhotosUseCase = GetPhotosUseCase(repo)
}