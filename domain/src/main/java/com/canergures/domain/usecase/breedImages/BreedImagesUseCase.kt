package com.canergures.domain.usecase.breedImages

import com.canergures.data.model.BreedImages
import com.canergures.data.repository.DataSource
import com.canergures.data.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BreedImagesUseCase @Inject constructor(
    private val dataRepository: DataSource
) : DogBreedImagesUseCase {
    override suspend fun getDogBreedImagesList(breed: String): Flow<Resource<List<BreedImages>>> {
        return dataRepository.getBreedImages(breed)
    }
}