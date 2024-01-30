package com.canergures.domain.usecase.breeds


import com.canergures.data.model.DogBreed
import com.canergures.data.repository.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BreedsUseCase @Inject constructor(
    private val dataRepository: DataSource
) : DogBreedUseCase {
    override fun getDogBreedsList(): Flow<List<DogBreed>> {
        return dataRepository.getBreedsList()
    }
}