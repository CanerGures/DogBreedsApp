package com.canergures.domain.usecase.breedImages

import com.canergures.data.model.BreedImages
import com.canergures.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface DogBreedImagesUseCase {
    suspend fun getDogBreedImagesList(breed: String): Flow<Resource<List<BreedImages>>>
}