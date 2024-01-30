package com.canergures.data.repository

import com.canergures.data.dataSource.local.source.LocalSource
import com.canergures.data.dataSource.remote.source.RemoteSource
import com.canergures.data.model.BreedImages
import com.canergures.data.model.DogBreed
import com.canergures.data.model.DogBreedImages
import com.canergures.data.model.FavouriteImages
import com.canergures.data.util.Converters
import com.canergures.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataRepository @Inject
constructor(
    private val remoteDataSource: RemoteSource,
    private val localDataSource: LocalSource
) : DataSource {

    override fun getBreedsList(): Flow<List<DogBreed>> {
        return localDataSource.getLocaleBreedsList().map {
            if (it.isEmpty()) {
                remoteDataSource.getBreedsList().data?.let { list ->
                    localDataSource.storeLocaleBreedList(list)
                }
            }
            it
        }.catch {
            emitAll(flowOf(emptyList()))
        }
    }

    override suspend fun getBreedImages(breedName: String): Flow<Resource<List<BreedImages>>> {
        localDataSource.getLocaleBreedImages(breedName).data?.let {
            if (it.isNotEmpty()) {
                return flow { emit(localDataSource.getLocaleBreedImages(breedName)) }.flowOn(
                    Dispatchers.IO
                )
            }
        }
        val converter = Converters()

        remoteDataSource.getBreedImages(breedName).data?.let {
            val list = it.map {string->
                BreedImages(
                    string,
                    false
                )
            }
            localDataSource.storeLocaleBreedList(
                DogBreedImages(
                    converter.toString(list),
                    breedName))
        }
        return flow { emit(localDataSource.getLocaleBreedImages(breedName)) }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateDogBreeds(dogName: String, isFavourite: Boolean) {
        localDataSource.updateLocaleBreed(dogName, isFavourite)
    }

    override fun getFavouriteBreeds(): Flow<List<DogBreed>> {
        return localDataSource.getLocaleFavouriteBreeds()
    }

    override fun getFavouriteDogBreedsImages(): Flow<List<FavouriteImages>> {
        return localDataSource.getFavouriteDogBreedsImages()
    }

    override suspend fun insertFavouriteDogBreedImages(favouriteImage: FavouriteImages) {
        localDataSource.insertFavouriteDogBreedImages(favouriteImage)
    }

    override fun deleteFavouriteDogBreedsImage(favouriteImage: FavouriteImages) {
        localDataSource.deleteFavouriteDogBreedsImage(favouriteImage)
    }

    override suspend fun updateFavouriteDogBreedsImage(breedName: String, selectedImage: BreedImages) {
        localDataSource.updateFavouriteDogBreedsImage(breedName, selectedImage)
    }
}