package com.canergures.dogbreedsapp.ui.feature.favouriteImage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canergures.data.model.FavouriteImages
import com.canergures.domain.usecase.favouriteImages.FavouriteBreedImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteImagesViewModel @Inject constructor(
    private val favouriteDogBreedsUseCase: FavouriteBreedImagesUseCase
) : ViewModel() {
    var uiState by mutableStateOf(FavouriteImagesUiState())
        private set

    init {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            favouriteDogBreedsUseCase.getFavouriteDogBreedsImages().collect { result ->
                uiState =
                    uiState.copy(
                        isLoading = false,
                        favouriteImages = result.map {
                            FavouriteImages(
                                imageUrl = it.imageUrl,
                                breedName = it.breedName,
                                isFavourite = it.isFavourite
                            )
                        })
            }
        }
    }

    fun removeImageFromFavourite(favouriteImage: FavouriteImages) {
        viewModelScope.launch {
            favouriteDogBreedsUseCase.deleteFavouriteDogBreedsImage(favouriteImage)
        }
    }
}