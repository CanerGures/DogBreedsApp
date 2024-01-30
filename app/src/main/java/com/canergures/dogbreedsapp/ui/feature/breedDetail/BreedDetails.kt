package com.canergures.dogbreedsapp.ui.feature.breedDetail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.imageLoader
import com.canergures.data.model.BreedImages
import com.canergures.data.model.FavouriteImages
import com.canergures.data.util.Constants.COMMA
import com.canergures.data.util.Constants.COMMA_WITH_SPACE
import com.canergures.data.util.Constants.TEXT_OFFLINE
import com.canergures.data.util.NetworkChecker
import com.canergures.dogbreedsapp.R
import com.canergures.dogbreedsapp.ui.theme.Purple200
import com.canergures.dogbreedsapp.ui.theme.blueText
import com.canergures.dogbreedsapp.ui.theme.card
import com.canergures.dogbreedsapp.ui.theme.favouriteColor
import com.canergures.dogbreedsapp.ui.theme.notFavouriteColor
import com.google.accompanist.pager.ExperimentalPagerApi

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BreedDetails(
    navController: NavController,
    dogBreedDetailsViewModel: BreedsDetailViewModel = hiltViewModel(),
    name: String,
    subBreeds: String,
    favourite: Boolean
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.text_details)) },
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
                        tint = Color.Black,
                    )
                }
            )
        },
        scaffoldState = scaffoldState,
        content = {
            NoConnectionView(scaffoldState)
            DetailsView(name, subBreeds, favourite, dogBreedDetailsViewModel)
        }
    )
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsView(
    name: String,
    subBreeds: String,
    favourite: Boolean,
    dogBreedDetailsViewModel: BreedsDetailViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            dogBreedDetailsViewModel.uiState.dogBreedsImages?.let { list ->
                item {
                    val state = com.google.accompanist.pager.rememberPagerState()
                    val iconSize = 30.dp
                    val offsetInPx = LocalDensity.current.run { (iconSize / 2).roundToPx() }
                    com.google.accompanist.pager.HorizontalPager(
                        count = list.size,
                        state = state
                    ) { page ->
                        var favouriteImage by remember { mutableStateOf(list[page].isFavourite) }
                        Box(modifier = Modifier.padding((iconSize / 2) + 8.dp)) {
                            Card (modifier = Modifier.clip(RoundedCornerShape(20.dp))){
                                AsyncImage(
                                    model = list[page].imageUrl.toUri(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(400.dp)
                                        .clip(RoundedCornerShape(20.dp)),
                                    contentDescription = "",
                                    imageLoader = LocalContext.current.imageLoader,
                                    alignment = Alignment.CenterStart,
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Image(
                                painter = if (favouriteImage) painterResource(id = R.drawable.ic_like) else painterResource(id = R.drawable.ic_not_like),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.offset {
                                    IntOffset(x = +offsetInPx, y = -offsetInPx)
                                }
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.White)
                                    .align(Alignment.TopEnd)
                                    .size(30.dp)
                                    .clickable(
                                        onClick = {
                                            if (!favouriteImage) {
                                                dogBreedDetailsViewModel.addBreedImageToFavourite(
                                                    FavouriteImages(
                                                        imageUrl = list[page].imageUrl,
                                                        breedName = name,
                                                        isFavourite = true
                                                    )
                                                )
                                                dogBreedDetailsViewModel.changeImageFavouriteState(
                                                    breedName = name,
                                                    selectedImage = BreedImages(
                                                       imageUrl = list[page].imageUrl,
                                                        isFavourite = true
                                                    )
                                                )
                                            } else {
                                                dogBreedDetailsViewModel.changeImageFavouriteState(
                                                    breedName = name,
                                                    selectedImage = BreedImages(
                                                        imageUrl = list[page].imageUrl,
                                                        isFavourite = false
                                                    )
                                                )
                                                dogBreedDetailsViewModel.deleteBreedImageToFavourite(
                                                    FavouriteImages(
                                                        imageUrl = list[page].imageUrl,
                                                        breedName = name,
                                                        isFavourite = true
                                                    )
                                                )
                                            }
                                            favouriteImage = !favouriteImage
                                            list[page].isFavourite = favouriteImage
                                        }
                                    ).padding(4.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))

                    DotsPagerIndicator(
                        totalDots = list.size,
                        selectedIndex = state.currentPage,
                        unSelectedColor = Purple200,
                        selectedColor = card,
                    )
                }
            }

            item {
                DogBreedNameCard(name)
            }

            item {
                Title(title = stringResource(id = R.string.text_sub_breeds))
                Spacer(modifier = Modifier.height(12.dp))

                val text = subBreeds.ifEmpty { stringResource(id = R.string.text_no_sub_breeds) }
                Text(
                    text = text.replace(COMMA, COMMA_WITH_SPACE),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp, 0.dp, 0.dp, 0.dp),
                    color = colorResource(id = R.color.text),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.W300,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(1f, false)
        ) {
            var favouriteBreed by remember { mutableStateOf(favourite) }
            Button(
                onClick = {
                    favouriteBreed = if (favouriteBreed) {
                        dogBreedDetailsViewModel.changeFavouriteState(dogName = name, false)
                        false
                    } else {
                        dogBreedDetailsViewModel.changeFavouriteState(dogName = name, true)
                        true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(12.dp, 0.dp, 12.dp, 0.dp),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.White,
                    backgroundColor = if (!favouriteBreed) favouriteColor else notFavouriteColor,
                )
            ) {
                if (!favouriteBreed) Text(stringResource(id = R.string.text_add_to_favourite)) else Text(
                    stringResource(id = R.string.text_remove_from_favourite)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DotsPagerIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    LazyRow(
        modifier = Modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .wrapContentHeight(align = Alignment.CenterVertically)

    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        textAlign = TextAlign.Start,
        color = blueText,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        fontWeight = FontWeight.W600,
        style = MaterialTheme.typography.subtitle1,
    )
}

@Composable
fun NoConnectionView(scaffoldState: ScaffoldState) {
    val context = LocalContext.current
    if (!NetworkChecker.isConnected(context)) {
        LaunchedEffect(key1 = true) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = TEXT_OFFLINE
            )
        }
    }
}