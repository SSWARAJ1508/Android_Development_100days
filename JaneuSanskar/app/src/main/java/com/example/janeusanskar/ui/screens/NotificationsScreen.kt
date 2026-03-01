package com.example.janeusanskar.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.janeusanskar.ui.theme.DeepSaffron
import com.example.janeusanskar.ui.theme.JaneuSanskarTheme

data class Notification(val title: String, val message: String, val time: String, val isImportant: Boolean = false)

val dummyNotifications = listOf(
    Notification("Reminder", "Janeu Sanskar will begin at 9:30 AM", "8:00 AM", true),
    Notification("Update", "Breakfast is being served near the main hall.", "8:30 AM"),
    Notification("Announcement", "Lunch will be served at 1 PM", "11:00 AM", true),
    Notification("Gallery Update", "New photos from the morning session are available.", "12:00 PM"),
    Notification("Info", "Please be seated for the next ritual.", "2:00 PM")
)

@Composable
fun NotificationsScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(dummyNotifications) { notification ->
            NotificationCard(notification = notification)
        }
    }
}

@Composable
private fun NotificationCard(notification: Notification) {
    val borderColor = if (notification.isImportant) DeepSaffron else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, borderColor),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification Icon",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = notification.title, style = MaterialTheme.typography.titleMedium)
                Text(text = notification.message, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = notification.time, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    JaneuSanskarTheme {
        NotificationsScreen()
    }
}
