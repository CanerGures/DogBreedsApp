package com.canergures.data.dataSource.remote.source

import com.canergures.data.model.DogBreed
import com.canergures.data.util.Resource

interface RemoteSource {
    suspend fun getBreedsList(): Resource<List<DogBreed>>
    suspend fun getBreedImages(breedName: String): Resource<List<String>>
}