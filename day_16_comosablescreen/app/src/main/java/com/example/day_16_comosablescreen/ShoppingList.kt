package com.example.day_16_comosablescreen

import androidx.compose.foundation.background
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

@Composable
fun ShoppingListScreen() {

    var itemText by remember { mutableStateOf("") }
    val shoppingItems = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F5F9))
            .padding(16.dp)
    ) {

        Text(
            text = "Shopping List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F172A)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Field
        OutlinedTextField(
            value = itemText,
            onValueChange = { itemText = it },
            label = { Text("Add Item") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Add Button
        Button(
            onClick = {
                if (itemText.isNotBlank()) {
                    shoppingItems.add(itemText)
                    itemText = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Add to List")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Shopping List
        LazyColumn {
            items(shoppingItems) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp,
                        color = Color(0xFF1E293B)
                    )
                }
            }
        }
    }
}
