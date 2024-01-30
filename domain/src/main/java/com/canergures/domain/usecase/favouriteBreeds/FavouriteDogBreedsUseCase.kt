package com.canergures.domain.usecase.favouriteBreeds

import com.canergures.data.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface FavouriteDogBreedsUseCase {
    fun getFavouriteBreeds(): Flow<List<DogBreed>>
    suspend fun changeBreedFavourite(name: String, isFavourite: Boolean)
}