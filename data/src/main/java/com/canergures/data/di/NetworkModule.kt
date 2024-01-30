package com.canergures.data.di

import com.canergures.data.dataSource.local.persistance.BreedsDao
import com.canergures.data.dataSource.local.source.LocalDataSource
import com.canergures.data.dataSource.local.source.LocalSource
import com.canergures.data.dataSource.remote.DogBreedsApi
import com.canergures.data.dataSource.remote.source.RemoteDataSource
import com.canergures.data.dataSource.remote.source.RemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient, url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideDogBreedsAPIService(retrofit: Retrofit): DogBreedsApi {
        return retrofit.create(DogBreedsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: DogBreedsApi): RemoteSource {
        return RemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: BreedsDao): LocalSource {
        return LocalDataSource(dao)
    }
}