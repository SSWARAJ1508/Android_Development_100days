package com.example.day_17_mutablelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
            ListComparisonApp()
        }
    }
}

@Composable
fun ListComparisonApp() {


    val normalList = remember {
        listOf("Apple", "Banana", "Orange")
    }

    // ✅ Mutable state list (UI WILL update)
    val mutableList = remember {
        mutableStateListOf("Milk", "Bread", "Eggs")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(16.dp)
    ) {

        Text(
            text = "Non-Mutable List",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = {
                // This creates a new list but UI won't recompose
                // because Compose doesn't track this list
                normalList + "New Item"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Item (No UI Update)")
        }

        LazyColumn {
            items(normalList) { item ->
                Text(
                    text = item,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Mutable State List",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = {
                mutableList.add("Item ${mutableList.size + 1}")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Item (UI Updates)")
        }

        LazyColumn {
            items(mutableList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}
