package com.canergures.dogbreedsapp.ui.feature.breedList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.canergures.dogbreedsapp.R

@Composable
fun TopBar(
    goFavourites: () -> Unit,
    goToFavouriteImages: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.top_bar_headline),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.surface
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 16.dp, 36.dp, 0.dp),
            horizontalArrangement = Arrangement.End
        ) {
            GoToFavourites { goFavourites() }
            Spacer(modifier = Modifier.padding(8.dp))
            GoToFavouriteImages { goToFavouriteImages() }
        }
    }
}

@Composable
fun GoToFavourites(goFavourites: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.ic_favourite),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(24.dp, 24.dp)
            .clickable(onClick = goFavourites),
    )
}

@Composable
fun GoToFavouriteImages(goToFavouriteImages: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.favorite_icon),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(24.dp, 24.dp)
            .clickable(onClick = goToFavouriteImages))
}

