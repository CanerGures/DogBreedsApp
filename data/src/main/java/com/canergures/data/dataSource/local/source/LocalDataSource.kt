package com.canergures.data.dataSource.local.source

import com.canergures.data.dataSource.local.persistance.BreedsDao
import com.canergures.data.model.BreedImages
import com.canergures.data.model.DogBreed
import com.canergures.data.model.DogBreedImages
import com.canergures.data.model.FavouriteImages
import com.canergures.data.util.Converters
import com.canergures.data.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject
constructor(private val dao: BreedsDao) : LocalSource {

    // Function for getting Dog Breeds List from Database
    override fun getLocaleBreedsList(): Flow<List<DogBreed>> {
        return dao.getDogBreedsList()
    }

    // Function for storing Dog Breeds List to Database
    override suspend fun storeLocaleBreedList(dogBreeds: List<DogBreed>) {
        dao.insertAllDogBreedList(dogBreeds)
    }

    // Function to get dog breed images from the database
    override suspend fun getLocaleBreedImages(breedName: String): Resource<List<BreedImages>> {
        val dogBreedImageList = dao.getDogBreedImagesList(breedName)
        return if (dogBreedImageList.isEmpty()) {
            Resource.DataError(errorCode = 2)
        } else {
            val converter = Converters()
            Resource.Success(converter.fromString(dogBreedImageList.first()))
        }
    }

    // Function for storing Dog Breed Images List to Database
    override suspend fun storeLocaleBreedList(breedImages: DogBreedImages) {
        dao.insertDogBreedImagesList(breedImages)
    }

    // Function to update dog breeds table for favourite value in the Database
    override suspend fun updateLocaleBreed(dogName: String, isFavourite: Boolean) {
        dao.updateDogBreeds(dogName, isFavourite)
    }

    // Function to get favourite dog breeds list from the Database
    override fun getLocaleFavouriteBreeds(): Flow<List<DogBreed>> {
        return dao.getFavouriteDogBreedsList()
    }

    override fun getFavouriteDogBreedsImages(): Flow<List<FavouriteImages>> {
        return dao.getFavouriteDogBreedsImages()
    }

    override suspend fun insertFavouriteDogBreedImages(favouriteImage: FavouriteImages) {
        dao.insertFavouriteDogBreedImages(favouriteImage)
    }

    override fun deleteFavouriteDogBreedsImage(favouriteImage: FavouriteImages) {
        dao.deleteFavouriteDogBreedsImage(favouriteImage)
    }

    override suspend fun updateFavouriteDogBreedsImage(breedName: String, selectedImage: BreedImages) {
        val dogBreedImageList = dao.getDogBreedImagesList(breedName.lowercase())
        val localList = Converters().fromString(dogBreedImageList.first())
        localList.map {
            if (it.imageUrl == selectedImage.imageUrl) {
                it.isFavourite = selectedImage.isFavourite
            }
        }
        val a = Converters().toString(localList)
        dao.updateDogBreedImagesList(
            breedName = breedName.lowercase(),
            imageUrlString = listOf(a)
        )
    }
}