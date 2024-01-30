package com.canergures.domain.di

import com.canergures.domain.usecase.breedImages.BreedImagesUseCase
import com.canergures.domain.usecase.breedImages.DogBreedImagesUseCase
import com.canergures.domain.usecase.breeds.BreedsUseCase
import com.canergures.domain.usecase.breeds.DogBreedUseCase
import com.canergures.domain.usecase.favouriteBreeds.FavouriteBreedsUseCase
import com.canergures.domain.usecase.favouriteBreeds.FavouriteDogBreedsUseCase
import com.canergures.domain.usecase.favouriteImages.FavouriteBreedImagesUseCase
import com.canergures.domain.usecase.favouriteImages.FavouriteDogBreedImagesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindFavouriteUseCase(favouriteUseCase: FavouriteBreedsUseCase): FavouriteDogBreedsUseCase

    @Binds
    @Singleton
    abstract fun bindBreedsUseCase(breedsUseCase: BreedsUseCase): DogBreedUseCase

    @Binds
    @Singleton
    abstract fun bindBreedImagesUseCase(breedImagesUseCase: BreedImagesUseCase): DogBreedImagesUseCase

    @Binds
    @Singleton
    abstract fun bindBreedFavouriteImagesUseCase(favouriteBreedImagesUseCase: FavouriteDogBreedImagesUseCase): FavouriteBreedImagesUseCase
}