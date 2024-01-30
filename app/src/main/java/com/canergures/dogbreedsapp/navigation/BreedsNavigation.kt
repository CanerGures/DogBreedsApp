package com.canergures.dogbreedsapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.canergures.data.util.Constants.DETAILS_ROUTE
import com.canergures.data.util.Constants.FAVOURITE
import com.canergures.data.util.Constants.MINUS_THREE_HUNDRED
import com.canergures.data.util.Constants.NAME
import com.canergures.data.util.Constants.SUB_BREEDS
import com.canergures.data.util.Constants.THREE_HUNDRED
import com.canergures.dogbreedsapp.ui.feature.breedDetail.BreedDetails
import com.canergures.dogbreedsapp.ui.feature.breedList.DogBreedsMainList
import com.canergures.dogbreedsapp.ui.feature.favouriteBreed.FavouriteBreeds
import com.canergures.dogbreedsapp.ui.feature.favouriteImage.FavouriteImages
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@Composable
fun BreedsNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController, startDestination = ScreenRoute.Home.route) {
        composable(
            ScreenRoute.Home.route,
            popEnterTransition = {
                fadeIn(animationSpec = tween(THREE_HUNDRED)) + slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    ),
                    initialOffsetX = { MINUS_THREE_HUNDRED },
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(THREE_HUNDRED)) + slideOutHorizontally(
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    ),
                    targetOffsetX = { MINUS_THREE_HUNDRED }
                )
            },
        ) {
            DogBreedsMainList(navController, hiltViewModel())
        }
        composable(
            "${ScreenRoute.Details.route}${DETAILS_ROUTE}",
            enterTransition = {
                fadeIn(animationSpec = tween(THREE_HUNDRED)) + slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    ),
                    initialOffsetX = { THREE_HUNDRED },
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(THREE_HUNDRED)) + slideOutHorizontally(
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    ),
                    targetOffsetX = { THREE_HUNDRED },
                )
            },
            arguments = listOf(navArgument(NAME) { type = NavType.StringType },
                navArgument(SUB_BREEDS) { type = NavType.StringType },
                navArgument(FAVOURITE) { type = NavType.BoolType })
        ) {
            BreedDetails(
                dogBreedDetailsViewModel = hiltViewModel(),
                name = it.arguments?.getString(NAME) ?: "",
                subBreeds = it.arguments?.getString(SUB_BREEDS) ?: "",
                favourite = it.arguments?.getBoolean(FAVOURITE) ?: false,
                navController = navController
            )
        }
        composable(
            ScreenRoute.Favourites.route,
            exitTransition = {
                fadeOut(animationSpec = tween(THREE_HUNDRED)) + slideOutHorizontally(
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    ),
                    targetOffsetX = { MINUS_THREE_HUNDRED },
                )
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(THREE_HUNDRED)) + slideInHorizontally(
                    initialOffsetX = { MINUS_THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                )
            },
        ) {
            FavouriteBreeds(navController = navController, hiltViewModel())
        }
        composable(
            ScreenRoute.FavouriteImage.route,
            popEnterTransition = {
                fadeIn(animationSpec = tween(THREE_HUNDRED)) + slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    ),
                    initialOffsetX = { MINUS_THREE_HUNDRED },
                )
            },
            exitTransition = {
                fadeOut(animationSpec = tween(THREE_HUNDRED)) + slideOutHorizontally(
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    ),
                    targetOffsetX = { MINUS_THREE_HUNDRED }
                )
            },
        ) {
            FavouriteImages(navController, hiltViewModel())
        }
    }
}