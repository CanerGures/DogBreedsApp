package com.canergures.data.util

import androidx.room.TypeConverter
import com.canergures.data.model.BreedImages
import com.canergures.data.util.Constants.COMMA

class Converters {
    /*@TypeConverter
    fun fromString(stringList: String): List<String> {
        return stringList.split(COMMA).map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = COMMA)
    }

     */

    @TypeConverter
    fun fromString(stringList: String): List<BreedImages> {
        val list1 = stringList.split(";").map { it }
        val list2 = list1.map { modelString ->
            BreedImages(
                imageUrl = modelString.split("#")[0],
                isFavourite = modelString.split("#")[1].toBoolean()
            )
        }
        return list2
    }

    @TypeConverter
    fun toString(breedImagesList: List<BreedImages>): String {
        val listOfStringBreedImage = breedImagesList.map {
            it.imageUrl + "#" + it.isFavourite
        }
        return listOfStringBreedImage.joinToString(separator = ";")
    }
}