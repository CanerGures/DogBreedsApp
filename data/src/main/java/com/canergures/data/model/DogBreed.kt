package com.canergures.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogBreed(
    @PrimaryKey(autoGenerate = false) val name: String,
    @ColumnInfo(name = "sub_breeds") val subBreeds: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "is_favourite") var isFavourite: Boolean
)