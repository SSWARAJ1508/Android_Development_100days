package com.example.geopulse.data.repository

import com.example.geopulse.data.remote.RetrofitClient

class NewsRepository {

    suspend fun fetchNews(apiKey: String) =
        RetrofitClient.api.getWarNews(apiKey = apiKey)
}