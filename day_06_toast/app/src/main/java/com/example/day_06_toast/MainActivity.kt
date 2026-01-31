package com.example.day_06_toast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedToastScreen()
        }
    }
}

@Composable
fun AdvancedToastScreen() {

    var showNotification by remember { mutableStateOf(false) }
    var notificationState by remember { mutableStateOf(NotificationState.LOADING) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFFF8FAFC),
                        Color(0xFFE2E8F0)
                    )
                )
            )
    ) {

        // 🌟 Center Card
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp),
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(28.dp)
                    .width(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Taskbug",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F172A)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Advanced In-App Notification",
                    fontSize = 14.sp,
                    color = Color(0xFF475569)
                )

                Spacer(modifier = Modifier.height(26.dp))

                Button(
                    onClick = {
                        showNotification = true
                        notificationState = NotificationState.LOADING
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Task", modifier = Modifier.padding(6.dp))
                }
            }
        }

        // 🔔 Notification Overlay
        AdvancedNotification(
            visible = showNotification,
            state = notificationState,
            title = if (notificationState == NotificationState.LOADING)
                "Saving task"
            else
                "Task saved",
            subtitle = if (notificationState == NotificationState.LOADING)
                "Please wait a moment"
            else
                "Your task was saved successfully",
            onDismiss = { showNotification = false }
        )
    }

    // Simulate progress → success
    LaunchedEffect(showNotification) {
        if (showNotification) {
            delay(1800)
            notificationState = NotificationState.SUCCESS
            delay(1600)
            showNotification = false
        }
    }
}
