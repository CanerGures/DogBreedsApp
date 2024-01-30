package com.canergures.data.util

import java.util.Locale

fun String.capitalizeFirstLetter(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}

fun String.generateSubBreedsCountText(): String {
    return "Has $this Sub Breeds"
}