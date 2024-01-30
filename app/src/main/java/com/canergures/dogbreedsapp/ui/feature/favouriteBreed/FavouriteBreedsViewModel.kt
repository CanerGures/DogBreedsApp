package com.canergures.dogbreedsapp.ui.feature.favouriteBreed

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canergures.data.model.DogBreed
import com.canergures.data.util.capitalizeFirstLetter
import com.canergures.domain.usecase.favouriteBreeds.FavouriteBreedsUseCase
import com.canergures.domain.usecase.favouriteBreeds.FavouriteDogBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteBreedsViewModel @Inject constructor(
    private val dogBreedsUseCase: FavouriteBreedsUseCase,
    private val favouriteDogBreedsUseCase: FavouriteDogBreedsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(FavouriteUiState())
        private set

    init {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            dogBreedsUseCase.getFavouriteBreeds().collect { result ->
                uiState =
                    uiState.copy(
                        isLoading = false,
                        dogBreeds = result.map {
                            DogBreed(
                                name = it.name.capitalizeFirstLetter(),
                                subBreeds = it.subBreeds,
                                imageUrl = it.imageUrl,
                                isFavourite = it.isFavourite
                            )
                        })
            }
        }
    }

    fun changeFavouriteState(dogName: String, isFavourite: Boolean) {
        viewModelScope.launch {
            favouriteDogBreedsUseCase.changeBreedFavourite(
                name = dogName,
                isFavourite = isFavourite
            )
        }
    }
}