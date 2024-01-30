package com.canergures.data.dataSource.local.source

import com.canergures.data.model.BreedImages
import com.canergures.data.model.DogBreed
import com.canergures.data.model.DogBreedImages
import com.canergures.data.model.FavouriteImages
import com.canergures.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface LocalSource {
    fun getLocaleBreedsList(): Flow<List<DogBreed>>
    suspend fun getLocaleBreedImages(breedName: String): Resource<List<BreedImages>>
    suspend fun storeLocaleBreedList(dogBreeds: List<DogBreed>)
    suspend fun storeLocaleBreedList(breedImages: DogBreedImages)
    suspend fun updateLocaleBreed(dogName: String, isFavourite: Boolean)
    fun getLocaleFavouriteBreeds(): Flow<List<DogBreed>>
    suspend fun insertFavouriteDogBreedImages(favouriteImage: FavouriteImages)
    fun getFavouriteDogBreedsImages(): Flow<List<FavouriteImages>>
    fun deleteFavouriteDogBreedsImage(favouriteImage: FavouriteImages)
    suspend fun updateFavouriteDogBreedsImage(breedName: String, selectedImage: BreedImages)
}