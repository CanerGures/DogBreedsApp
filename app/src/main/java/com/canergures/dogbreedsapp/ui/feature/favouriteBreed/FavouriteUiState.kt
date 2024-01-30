package com.canergures.dogbreedsapp.ui.feature.favouriteBreed

import com.canergures.data.model.DogBreed

data class FavouriteUiState(
    val dogBreeds: List<DogBreed>? = null,
    val isLoading: Boolean = false,
)