package com.example.day_13_intents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = intent.getStringExtra("username") ?: "User"

        setContent {
            MaterialTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier.padding(20.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Text(
                            text = "Welcome, $username 👋",
                            modifier = Modifier.padding(24.dp),
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
    }
}
