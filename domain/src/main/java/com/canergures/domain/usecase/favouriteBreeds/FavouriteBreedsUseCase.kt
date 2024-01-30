package com.canergures.domain.usecase.favouriteBreeds

import com.canergures.data.model.DogBreed
import com.canergures.data.repository.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteBreedsUseCase @Inject constructor(
    private val dataRepository: DataSource
) : FavouriteDogBreedsUseCase {
    override fun getFavouriteBreeds(): Flow<List<DogBreed>> {
        return dataRepository.getFavouriteBreeds()
    }

    override suspend fun changeBreedFavourite(name: String, isFavourite: Boolean) {
        dataRepository.updateDogBreeds(dogName = name, isFavourite = isFavourite)
    }
}