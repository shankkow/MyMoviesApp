package com.example.moviesapp.di

import com.example.moviesapp.data.repository.PeopleRepository
import com.example.moviesapp.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePeopleRepository(apiService: ApiService): PeopleRepository {
        return PeopleRepository(apiService)
    }
}
