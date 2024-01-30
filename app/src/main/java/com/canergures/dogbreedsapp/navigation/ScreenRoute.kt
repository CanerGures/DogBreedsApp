package com.canergures.dogbreedsapp.navigation

import androidx.annotation.StringRes
import com.canergures.data.util.Constants.DETAILS
import com.canergures.data.util.Constants.FAVOURITES
import com.canergures.data.util.Constants.FAVOURITE_IMAGES
import com.canergures.data.util.Constants.HOME
import com.canergures.dogbreedsapp.R

sealed class ScreenRoute(val route: String, @StringRes val resourceId: Int) {
    object Home : ScreenRoute(HOME, R.string.text_home)
    object Details : ScreenRoute(DETAILS, R.string.text_details)
    object Favourites : ScreenRoute(FAVOURITES, R.string.text_favourites)
    object FavouriteImage : ScreenRoute(FAVOURITE_IMAGES, R.string.text_favourite_images)
}