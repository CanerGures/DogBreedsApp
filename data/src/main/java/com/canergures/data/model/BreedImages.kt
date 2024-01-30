package com.canergures.data.model

import androidx.room.ColumnInfo

data class BreedImages (
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "is_favourite") var isFavourite: Boolean
)