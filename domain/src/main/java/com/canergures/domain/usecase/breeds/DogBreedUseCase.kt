package com.canergures.domain.usecase.breeds


import com.canergures.data.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface DogBreedUseCase {
    fun getDogBreedsList(): Flow<List<DogBreed>>
}