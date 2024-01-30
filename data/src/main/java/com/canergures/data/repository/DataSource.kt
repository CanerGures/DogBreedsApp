package com.canergures.data.repository

import com.canergures.data.model.BreedImages
import com.canergures.data.model.DogBreed
import com.canergures.data.model.FavouriteImages
import com.canergures.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getBreedsList(): Flow<List<DogBreed>>
    suspend fun getBreedImages(breedName: String): Flow<Resource<List<BreedImages>>>
    suspend fun updateDogBreeds(dogName: String, isFavourite: Boolean)
    fun getFavouriteBreeds(): Flow<List<DogBreed>>
    suspend fun insertFavouriteDogBreedImages(favouriteImage: FavouriteImages)
    fun getFavouriteDogBreedsImages(): Flow<List<FavouriteImages>>
    fun deleteFavouriteDogBreedsImage(favouriteImage: FavouriteImages)
    suspend fun updateFavouriteDogBreedsImage(breedName: String, selectedImage: BreedImages)
}