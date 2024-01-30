package com.canergures.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogBreedImages(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "dog_breed_image_urls") val imageUrls: String,
    @ColumnInfo(name = "breed_name") val breedName: String
)