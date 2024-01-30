package com.canergures.domain.usecase.favouriteImages

import com.canergures.data.model.BreedImages
import com.canergures.data.model.FavouriteImages
import com.canergures.data.repository.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteDogBreedImagesUseCase @Inject constructor(
    private val dataRepository: DataSource
): FavouriteBreedImagesUseCase {
    override suspend fun insertFavouriteDogBreedImages(favouriteImage: FavouriteImages) {
        dataRepository.insertFavouriteDogBreedImages(favouriteImage)
    }

    override fun getFavouriteDogBreedsImages(): Flow<List<FavouriteImages>> {
        return dataRepository.getFavouriteDogBreedsImages()
    }

    override fun deleteFavouriteDogBreedsImage(favouriteImage: FavouriteImages) {
        dataRepository.deleteFavouriteDogBreedsImage(favouriteImage)
    }

    override suspend fun updateFavouriteDogBreedsImage(breedName: String, selectedImage: BreedImages) {
        dataRepository.updateFavouriteDogBreedsImage(breedName, selectedImage)
    }

}