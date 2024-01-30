package com.canergures.dogbreedsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.canergures.data.util.Constants.DARK_THEME
import com.canergures.dogbreedsapp.navigation.BreedsNavigation
import com.canergures.dogbreedsapp.ui.theme.DogBreedsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogBreedsAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BreedsNavigation()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(DARK_THEME, widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    DogBreedsAppTheme(darkTheme = true) {
        BreedsNavigation()
    }
}
