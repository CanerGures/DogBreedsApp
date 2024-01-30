package com.canergures.data.di

import com.canergures.data.repository.DataRepository
import com.canergures.data.repository.DataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(dogBreedRepository: DataRepository): DataSource
}