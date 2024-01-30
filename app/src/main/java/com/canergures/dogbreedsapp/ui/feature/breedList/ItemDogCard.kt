package com.canergures.dogbreedsapp.ui.feature.breedList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.canergures.data.model.DogBreed
import com.canergures.data.util.Constants.COMMA
import com.canergures.data.util.Constants.ONE
import com.canergures.data.util.Constants.ZERO
import com.canergures.data.util.generateSubBreedsCountText
import com.canergures.dogbreedsapp.R
import com.canergures.dogbreedsapp.ui.theme.favouriteColor
import com.canergures.dogbreedsapp.ui.theme.notFavouriteColor

@Composable
fun ItemDogCard(
    dog: DogBreed,
    onItemClicked: (dog: DogBreed) -> Unit,
    favoriteDogBreed: (breedName: String, isFavourite: Boolean) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = { onItemClicked(dog) }),
        elevation = 16.dp,
    ) {
        val image: Painter = rememberAsyncImagePainter(dog.imageUrl)
        Row {
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = dog.name,
                    modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = getSubBreedsCountText(dog.subBreeds),
                    modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                    color = Color.White,
                    style = typography.caption
                )

                var isFavourite by remember { mutableStateOf(dog.isFavourite) }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (isFavourite) {
                            favoriteDogBreed(dog.name, false)
                        } else {
                            favoriteDogBreed(dog.name, true)
                        }
                        isFavourite = !isFavourite
                        dog.isFavourite = isFavourite
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .padding(8.dp, 0.dp, 0.dp, 12.dp),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = if (!isFavourite) favouriteColor else notFavouriteColor,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    if (!isFavourite) Text(
                        stringResource(id = R.string.text_add_to_favourite),
                        fontSize = 8.sp,
                    ) else Text(
                        stringResource(id = R.string.text_remove_from_favourite),
                        fontSize = 8.sp,
                    )
                }
            }
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 120.dp, topEnd = 20.dp, bottomStart = 0.dp, bottomEnd = 20.dp
                        )
                    )
            )
        }
    }
}

private fun getSubBreedsCountText(dogSubBreeds: String): String {
    return when {
        dogSubBreeds.isNotEmpty() && COMMA in dogSubBreeds -> dogSubBreeds.split(COMMA).size.toString()
        dogSubBreeds.isNotEmpty() -> ONE
        else -> ZERO
    }.generateSubBreedsCountText()
}
