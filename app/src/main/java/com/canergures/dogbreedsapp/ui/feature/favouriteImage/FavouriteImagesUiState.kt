package com.canergures.dogbreedsapp.ui.feature.favouriteImage

import com.canergures.data.model.FavouriteImages

data class FavouriteImagesUiState(
    val favouriteImages: List<FavouriteImages>? = null,
    val isLoading: Boolean = false,
)