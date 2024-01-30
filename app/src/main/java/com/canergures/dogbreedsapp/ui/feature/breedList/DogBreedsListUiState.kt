package com.canergures.dogbreedsapp.ui.feature.breedList

import com.canergures.data.model.DogBreed


data class DogBreedsListUiState(
    var dogBreedsState: List<DogBreed>? = null,
    val isLoading: Boolean = true
)