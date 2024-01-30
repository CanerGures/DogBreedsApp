package com.canergures.dogbreedsapp.ui.feature.breedList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canergures.data.model.DogBreed
import com.canergures.data.util.capitalizeFirstLetter
import com.canergures.domain.usecase.breeds.BreedsUseCase
import com.canergures.domain.usecase.favouriteBreeds.FavouriteDogBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DogBreedsListViewModel @Inject constructor(
    private val dogBreedsUseCase: BreedsUseCase,
    private val favouriteDogBreedsUseCase: FavouriteDogBreedsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(DogBreedsListUiState())
        private set

    init {
        getDogBreedsList()
    }

    fun getDogBreedsList() {
        // CoroutineScope tied to this ViewModel.
        // This scope will be canceled when ViewModel will be cleared, i.e ViewModel.onCleared is called
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            dogBreedsUseCase.getDogBreedsList().collect { result ->
                uiState = if (result.isNotEmpty()) {
                    uiState.copy(
                        isLoading = false,
                        dogBreedsState = result.map { // Returns a list containing the results of applying
                            // the given transform function to each element in the original collection.
                            DogBreed(
                                subBreeds = it.subBreeds,
                                isFavourite = it.isFavourite,
                                name = it.name.capitalizeFirstLetter(),
                                imageUrl = it.imageUrl,
                            )
                        })
                } else {
                    uiState.copy(isLoading = false, dogBreedsState = result)
                }
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