package com.canergures.dogbreedsapp.ui.feature.breedList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canergures.data.util.Constants.FAVOURITES
import com.canergures.data.util.Constants.FAVOURITE_IMAGES
import com.canergures.data.util.NetworkChecker.Network.isConnected
import com.canergures.dogbreedsapp.R

@Composable
fun DogBreedsMainList(
    navController: NavHostController,
    dogBreedListViewModel: DogBreedsListViewModel = hiltViewModel()
) {
    Column {
        TopBar(
            goFavourites = {
                navController.navigate(FAVOURITES)
            },
            goToFavouriteImages = {
                navController.navigate(FAVOURITE_IMAGES)
            }
        )
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                if (dogBreedListViewModel.uiState.isLoading) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillParentMaxSize()
                            .background(Color.White)
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            item {
                NoInternetConnectionView(dogBreedsListViewModel = dogBreedListViewModel)
            }

            dogBreedListViewModel.uiState.dogBreedsState?.let { list ->
                items(list) {
                    ItemDogCard(
                        it,
                        favoriteDogBreed = { breedName, isFavourite ->
                            dogBreedListViewModel.changeFavouriteState(breedName, isFavourite)
                        },
                        onItemClicked = { dog ->
                            navController.navigate("details/${dog.name}/${dog.isFavourite}?subBreeds=${dog.subBreeds}")
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun NoInternetConnectionView(dogBreedsListViewModel: DogBreedsListViewModel) {
    val context = LocalContext.current
    if (!isConnected(context) && dogBreedsListViewModel.uiState.dogBreedsState?.isEmpty() == true) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.no_wifi),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.wrapContentSize(),
                    alpha = 0.6F
                )
                Text(
                    text = stringResource(id = R.string.text_connection_failed),
                    modifier = Modifier.wrapContentSize()
                )
            }

            Button(
                onClick = { dogBreedsListViewModel.getDogBreedsList() },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 8.dp,
                    disabledElevation = 0.dp,
                    pressedElevation = 2.dp,
                ),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            ) {
                Text(
                    color = Color.White,
                    text = stringResource(R.string.text_retry),
                    style = MaterialTheme.typography.subtitle1,
                )
            }
        }
    }
}


