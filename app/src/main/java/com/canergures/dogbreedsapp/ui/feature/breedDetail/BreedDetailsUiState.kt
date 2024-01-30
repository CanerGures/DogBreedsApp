package com.canergures.dogbreedsapp.ui.feature.breedDetail

import com.canergures.data.model.BreedImages

data class BreedDetailsUiState(
    val dogBreedsImages: List<BreedImages>? = null,
    val isLoading: Boolean = false,
)