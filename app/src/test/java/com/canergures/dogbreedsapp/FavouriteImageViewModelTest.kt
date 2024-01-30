package com.canergures.dogbreedsapp

import com.canergures.dogbreedsapp.ui.feature.favouriteImage.FavouriteImagesViewModel
import com.canergures.domain.usecase.favouriteImages.FavouriteBreedImagesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class FavouriteImagesViewModelTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var viewModel: FavouriteImagesViewModel
    private val favouriteDogBreedsUseCase: FavouriteBreedImagesUseCase = mockk(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockKAnnotations.init(this)
        viewModel = FavouriteImagesViewModel(favouriteDogBreedsUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `test initialization`() {
        coEvery { favouriteDogBreedsUseCase.getFavouriteDogBreedsImages() } returns flowOf(emptyList())

        // When
        viewModel = FavouriteImagesViewModel(favouriteDogBreedsUseCase)
        // Verify that the view model is initialized with the correct state
        assertTrue(
            "favouriteImages should be empty or null",
            viewModel.uiState.favouriteImages.isNullOrEmpty()
        )

    }

    @Test
    fun `test fetching data`() = runBlocking<Unit> {
        // Given
        coEvery { favouriteDogBreedsUseCase.getFavouriteDogBreedsImages() } returns flowOf(emptyList())

        // When
        viewModel = FavouriteImagesViewModel(favouriteDogBreedsUseCase)

        // Then
        assert(!viewModel.uiState.isLoading)
        viewModel.uiState.favouriteImages?.let { assert(it.isEmpty()) }
    }
}