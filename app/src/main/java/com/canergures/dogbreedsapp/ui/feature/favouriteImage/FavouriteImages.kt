package com.canergures.dogbreedsapp.ui.feature.favouriteImage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.canergures.data.model.FavouriteImages
import com.canergures.dogbreedsapp.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavouriteImages(
    navController: NavController,
    favouritesViewModel: FavouriteImagesViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.text_favourite_images)) },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = Color.Black,
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                navController.navigateUp()
                            },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            )
        },

        content = {
            favouritesViewModel.uiState.favouriteImages?.let {
                FavouriteDogBreedsImages(favouritesViewModel, it)
            }
        }
    )
}

@Composable
fun FavouriteDogBreedsImages(
    favouritesViewModel: FavouriteImagesViewModel,
    list: List<FavouriteImages>
) {
    if (list.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.broken_hearth),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.wrapContentSize(),
                alpha = 0.6F
            )
            Text(
                text = stringResource(id = R.string.text_has_no_favourite_images),
                modifier = Modifier.wrapContentSize()
            )
        }
    } else {
        LazyColumn {
            items(list) {
                FavouriteImageCard(
                    dog = it,
                    favoriteDogBreed = { favouriteImage ->
                        favouritesViewModel.removeImageFromFavourite(favouriteImage)
                    }
                )
            }
        }
    }
}
