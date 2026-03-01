package com.example.geopulse.ui.news

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.geopulse.data.model.NewsArticle

@Composable
fun NewsList(
    articles: List<NewsArticle>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(articles) { article ->
            NewsCard(
                title = article.title,
                description = article.description ?: "No description available"
            )
        }
    }
}