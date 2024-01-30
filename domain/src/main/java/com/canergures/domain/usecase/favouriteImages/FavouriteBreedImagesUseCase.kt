package com.canergures.domain.usecase.favouriteImages

import com.canergures.data.model.BreedImages
import com.canergures.data.model.FavouriteImages
import kotlinx.coroutines.flow.Flow

interface FavouriteBreedImagesUseCase {
    suspend fun insertFavouriteDogBreedImages(favouriteImage: FavouriteImages)
    fun getFavouriteDogBreedsImages(): Flow<List<FavouriteImages>>
    fun deleteFavouriteDogBreedsImage(favouriteImage: FavouriteImages)
    suspend fun updateFavouriteDogBreedsImage(breedName: String, selectedImage: BreedImages)
}