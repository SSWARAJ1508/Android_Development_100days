package com.example.day_20_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VioletYellowApp()
        }
    }
}

@Composable
fun VioletYellowApp() {

    var selectedItem by remember { mutableStateOf("") }

    val tasks = listOf(
        "Learn Jetpack Compose",
        "Understand State",
        "Practice UI Design",
        "Revise Android Basics",
        "Build Confidence"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5E2B97)) // Violet background
            .padding(16.dp)
    ) {

        Column {

            Text(
                text = "Day 20 – Compose UI",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFEB3B) // Yellow
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Violet & Yellow themed UI",
                fontSize = 16.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tasks) { task ->
                    TaskCard(
                        title = task,
                        isSelected = task == selectedItem,
                        onClick = { selectedItem = task }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            SelectedInfo(selectedItem)
        }
    }
}

@Composable
fun TaskCard(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                Color(0xFFFFF176) // Darker Yellow
            else
                Color(0xFFFFF9C4) // Light Yellow
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF4A148C) // Deep violet text
            )
        }
    }
}

@Composable
fun SelectedInfo(selectedItem: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF7E57C2)),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Selected Task",
                color = Color(0xFFFFEB3B),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (selectedItem.isEmpty())
                    "Tap on a task above"
                else
                    selectedItem,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
