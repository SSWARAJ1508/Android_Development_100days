package com.example.day_05_lazy_layouts


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlaceItem(place: TouristPlace) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(140.dp)
            .background(Color(0xFFE3F2FD)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = place.imageRes),
            contentDescription = place.name,
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = place.name,
            fontSize = 14.sp
        )
    }
}
