package com.example.day_21_state_management

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
            Day21App()
        }
    }
}

@Composable
fun Day21App() {
    val tasks = remember {
        mutableStateListOf(
            Task("Learn Compose"),
            Task("Practice State"),
            Task("Fix Fundamentals"),
            Task("Prepare for Interview")
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF1F8E9)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Day 21 – State Management",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF33691E)
            )

            Spacer(modifier = Modifier.height(16.dp))

            tasks.forEachIndexed { index, task ->
                TaskCard(
                    task = task,
                    onToggle = {
                        tasks[index] = task.copy(isDone = !task.isDone)
                    }
                )
            }
        }
    }
}

@Composable
fun TaskCard(task: Task, onToggle: () -> Unit) {
    val backgroundColor =
        if (task.isDone) Color(0xFFC8E6C9) else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onToggle() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = task.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = if (task.isDone) "Completed" else "Pending",
                    fontSize = 13.sp,
                    color = if (task.isDone) Color(0xFF2E7D32) else Color.Gray
                )
            }

            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onToggle() }
            )
        }
    }
}

data class Task(
    val title: String,
    val isDone: Boolean = false
)
