package com.example.geopulse.ui.news

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = NewsViewModel()
) {
    val newsList by viewModel.newsList
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    var selectedCountry by remember { mutableStateOf<Country?>(null) }

    // 🔑 COUNTRY → KEYWORDS MAP (FRONTEND LOGIC)
    val countryKeywords = mapOf(
        "India" to listOf("india", "delhi", "kashmir"),
        "Israel" to listOf("israel", "gaza", "hamas"),
        "Iran" to listOf("iran", "tehran"),
        "Russia" to listOf("russia", "moscow", "ukraine"),
        "Ukraine" to listOf("ukraine", "kyiv"),
        "Pakistan" to listOf("pakistan", "islamabad"),
        "China" to listOf("china", "beijing", "taiwan"),
        "United States" to listOf("us", "usa", "america", "pentagon")
    )

    // ✅ CORRECT FILTERING
    val displayNews =
        if (selectedCountry == null) {
            newsList
        } else {
            val keywords = countryKeywords[selectedCountry!!.name] ?: emptyList()

            val filtered = newsList.filter { news ->
                keywords.any { keyword ->
                    news.title.lowercase().contains(keyword) ||
                            news.description.lowercase().contains(keyword)
                }
            }

            if (filtered.isEmpty()) newsList else filtered
        }

    LaunchedEffect(Unit) {
        viewModel.loadNews()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("GeoPulse 🌍") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    selectedCountry = null
                    viewModel.loadNews()
                }
            ) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            // 🌍 COUNTRY FILTER BAR
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                FilterChip(
                    selected = selectedCountry == null,
                    onClick = { selectedCountry = null },
                    label = { Text("🌍 All") },
                    modifier = Modifier.padding(end = 8.dp)
                )

                famousCountries.forEach { country ->
                    FilterChip(
                        selected = selectedCountry == country,
                        onClick = { selectedCountry = country },
                        label = { Text("${country.flag} ${country.name}") },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            // 🧠 STATUS TEXT
            Text(
                text = selectedCountry?.let {
                    "Filtered for ${it.name}"
                } ?: "Showing global conflict news",
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium
            )

            // 📰 CONTENT
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "Error",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        items(displayNews) { news ->
                            NewsCard(
                                title = news.title,
                                description = news.description
                            )
                        }
                    }
                }
            }
        }
    }
}