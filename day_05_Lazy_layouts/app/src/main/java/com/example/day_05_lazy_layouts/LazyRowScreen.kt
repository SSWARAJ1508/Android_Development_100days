package com.example.day_05_lazy_layouts



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LazyRowScreen(places: List<TouristPlace>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(places.size) {
            PlaceItem(places[it])
        }
    }
}
