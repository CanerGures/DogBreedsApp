package com.canergures.data.di

import android.content.Context
import androidx.room.Room
import com.canergures.data.dataSource.local.persistance.DogBreedsListDataBase
import com.canergures.data.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DogBreedsListDataBase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideYourDao(db: DogBreedsListDataBase) =
        db.dogBreedsListDao()
}