package com.canergures.dogbreedsapp.ui.feature.breedDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canergures.data.model.BreedImages
import com.canergures.data.model.FavouriteImages
import com.canergures.data.util.Resource
import com.canergures.domain.usecase.breedImages.DogBreedImagesUseCase
import com.canergures.domain.usecase.favouriteBreeds.FavouriteBreedsUseCase
import com.canergures.domain.usecase.favouriteImages.FavouriteDogBreedImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dogBreedImagesUseCase: DogBreedImagesUseCase,
    private val favouriteDogBreedsUseCase: FavouriteBreedsUseCase,
    private val favouriteDogBreedImagesUseCase: FavouriteDogBreedImagesUseCase
) : ViewModel() {

    private val nameType = checkNotNull(savedStateHandle["name"]).toString()

    var uiState by mutableStateOf(BreedDetailsUiState())
        private set

    init {
        getDogBreedImages(nameType.lowercase())
    }

    fun getDogBreedImages(name: String) {
        viewModelScope.launch {
            dogBreedImagesUseCase.getDogBreedImagesList(name).collect { result ->
                handleDogBreedImagesResponse(result)
            }
        }
    }

    private fun handleDogBreedImagesResponse(result: Resource<List<BreedImages>>) {
        uiState = when (result) {
            is Resource.Success -> {
                uiState.copy(
                    isLoading = false,
                    dogBreedsImages = result.data
                )
            }

            is Resource.DataError -> {
                result.errorCode
                uiState.copy(isLoading = false, dogBreedsImages = emptyList())
            }

            is Resource.Loading -> {
                uiState.copy(isLoading = true)
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

    fun addBreedImageToFavourite(favouriteImages: FavouriteImages) {
        viewModelScope.launch {
            favouriteDogBreedImagesUseCase.insertFavouriteDogBreedImages(favouriteImages)
        }
    }

    fun changeImageFavouriteState(breedName: String, selectedImage: BreedImages) {
        viewModelScope.launch {
            favouriteDogBreedImagesUseCase.updateFavouriteDogBreedsImage(breedName, selectedImage)
        }
    }

    fun deleteBreedImageToFavourite(favouriteImages: FavouriteImages) {
        viewModelScope.launch {
            favouriteDogBreedImagesUseCase.deleteFavouriteDogBreedsImage(favouriteImages)
        }
    }
}