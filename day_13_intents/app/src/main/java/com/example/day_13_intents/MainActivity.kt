package com.example.day_13_intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Day 13 – Intents",
            style = MaterialTheme.typography.headlineMedium
        )

        // Explicit Intent
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("username", "Shubham")
                context.startActivity(intent)
            }
        ) {
            Text("Open Second Activity")
        }

        // Implicit Intent – Open Browser
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://developer.android.com")
                )
                context.startActivity(intent)
            }
        ) {
            Text("Open Android Docs")
        }

        // Implicit Intent – Dialer
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:9876543210")
                )
                context.startActivity(intent)
            }
        ) {
            Text("Open Dialer")
        }

        // Implicit Intent – Share Text
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Learning Android Intents 🚀")
                }
                context.startActivity(
                    Intent.createChooser(intent, "Share via")
                )
            }
        ) {
            Text("Share Text")
        }
    }
}
