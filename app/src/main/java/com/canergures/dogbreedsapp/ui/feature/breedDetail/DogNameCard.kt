package com.canergures.dogbreedsapp.ui.feature.breedDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DogBreedNameCard(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                text = name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.surface,
            )
        }
    }
}