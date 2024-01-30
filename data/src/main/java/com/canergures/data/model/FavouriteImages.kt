package com.canergures.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class FavouriteImages(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "dog_breed_image_url") val imageUrl: String,
    @ColumnInfo(name = "breed_name") val breedName: String,
    @ColumnInfo(name = "is_favourite") val isFavourite: Boolean
)