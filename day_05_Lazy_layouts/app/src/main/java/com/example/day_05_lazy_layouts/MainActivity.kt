package com.example.day_05_lazy_layouts



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristApp()
        }
    }
}

@Composable
fun TouristApp() {

    val places = listOf(
        TouristPlace("Place 1", R.drawable.pic1),
        TouristPlace("Place 2", R.drawable.pic2),
        TouristPlace("Place 3", R.drawable.pic3),
        TouristPlace("Place 4", R.drawable.pic4),
        TouristPlace("Place 5", R.drawable.pic6),
        TouristPlace("Place 6", R.drawable.pic7),
        TouristPlace("Place 7", R.drawable.pic8),
        TouristPlace("Place 8", R.drawable.pic5),
        TouristPlace("Place 9", R.drawable.pic9),
        TouristPlace("Place 10", R.drawable.pic10)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Lazy Row",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        LazyRowScreen(places)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Lazy Column",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        LazyColumnScreen(places)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Lazy Grid",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        LazyGridScreen(places)
    }
}
