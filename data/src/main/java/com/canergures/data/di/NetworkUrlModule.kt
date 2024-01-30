package com.canergures.data.di

import com.canergures.data.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkUrlModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return BASE_URL
    }
}