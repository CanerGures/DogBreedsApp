package com.canergures.dogbreedsapp.ui.feature.favouriteImage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.canergures.data.model.FavouriteImages
import com.canergures.dogbreedsapp.R

@Composable
fun FavouriteImageCard(
    dog: FavouriteImages,
    favoriteDogBreed: (favouriteImage: FavouriteImages) -> Unit,
) {

    val image: Painter = rememberAsyncImagePainter(dog.imageUrl)
    var isFavourite by remember { mutableStateOf(dog.isFavourite) }
    val iconSize = 30.dp
    val offsetInPx = LocalDensity.current.run { (iconSize / 2).roundToPx() }
    Box(modifier = Modifier.padding((iconSize / 2) + 8.dp)) {
        Card(modifier = Modifier.clip(RoundedCornerShape(20.dp)), elevation = 16.dp) {
            Image(
                painter = image,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentDescription = "",
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.Crop
            )
        }
        Image(
            painter = if (dog.isFavourite) painterResource(id = R.drawable.ic_like) else painterResource(
                id = R.drawable.ic_not_like
            ),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .offset {
                    IntOffset(x = +offsetInPx, y = -offsetInPx)
                }
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .align(Alignment.TopEnd)
                .size(30.dp)
                .clickable(
                    onClick = {
                        favoriteDogBreed(dog)
                        isFavourite = !isFavourite
                    }
                )
                .padding(4.dp)
        )
    }
}