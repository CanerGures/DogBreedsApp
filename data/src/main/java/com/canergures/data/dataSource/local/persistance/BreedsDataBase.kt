package com.canergures.data.dataSource.local.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.canergures.data.model.DogBreed
import com.canergures.data.model.DogBreedImages
import com.canergures.data.model.FavouriteImages
import com.canergures.data.util.Converters

@Database(version = 1, entities = [DogBreed::class, DogBreedImages::class, FavouriteImages::class], exportSchema = false)
@TypeConverters(Converters::class)
abstract class DogBreedsListDataBase : RoomDatabase() {

    abstract fun dogBreedsListDao(): BreedsDao
}