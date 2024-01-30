package com.canergures.dogbreedsapp

import com.canergures.data.model.DogBreed
import com.canergures.dogbreedsapp.ui.feature.favouriteBreed.FavouriteBreedsViewModel
import com.canergures.domain.usecase.favouriteBreeds.FavouriteBreedsUseCase
import com.canergures.domain.usecase.favouriteBreeds.FavouriteDogBreedsUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FavouriteBreedsViewModelTest {

    private lateinit var viewModel: FavouriteBreedsViewModel
    private val dogBreedsUseCase: FavouriteBreedsUseCase = mockk()
    private val favouriteDogBreedsUseCase: FavouriteDogBreedsUseCase = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        coEvery { dogBreedsUseCase.getFavouriteBreeds() } returns flowOf(emptyList())
        viewModel = FavouriteBreedsViewModel(dogBreedsUseCase, favouriteDogBreedsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initialization`() {
        coEvery { dogBreedsUseCase.getFavouriteBreeds() } returns flowOf(emptyList())

        viewModel = FavouriteBreedsViewModel(dogBreedsUseCase, favouriteDogBreedsUseCase)

        viewModel.uiState.dogBreeds?.let { assert(it.isEmpty()) }
    }

    @Test
    fun `test fetching data`() = runBlocking<Unit> {
        val mockDogBreeds = listOf(
            DogBreed("Labrador", "Golden", "labrador.jpg", true),
            DogBreed("Bulldog", "", "bulldog.jpg", false)
        )

        every { dogBreedsUseCase.getFavouriteBreeds() } returns flow {
            emit(mockDogBreeds)
        }

        viewModel = FavouriteBreedsViewModel(dogBreedsUseCase, favouriteDogBreedsUseCase)
        assertTrue(viewModel.uiState.dogBreeds == mockDogBreeds)
    }

    @Test
    fun `test changeFavouriteState`() {
        val dogName = "Labrador"
        val isFavourite = true

        coEvery {
            favouriteDogBreedsUseCase.changeBreedFavourite(
                name = dogName,
                isFavourite = isFavourite
            )
        } returns Unit

        viewModel.changeFavouriteState(dogName, isFavourite)
    }
}