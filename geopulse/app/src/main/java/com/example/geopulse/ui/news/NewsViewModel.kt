package com.example.geopulse.ui.news

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.geopulse.data.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class SimpleNews(
    val title: String,
    val description: String
)

class NewsViewModel : ViewModel() {

    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)
    val newsList = mutableStateOf<List<SimpleNews>>(emptyList())

    private val API_KEY = "c26a51623977479a905ad753274b97f5"

    fun loadNews() {
        isLoading.value = true
        errorMessage.value = null

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.api.getWarNews(apiKey = API_KEY)

                val articles = response.articles.take(20).map {
                    SimpleNews(
                        title = it.title ?: "No title",
                        description = it.description ?: "No description"
                    )
                }

                // 🔥 SWITCH TO MAIN THREAD FOR STATE UPDATE
                withContext(Dispatchers.Main) {
                    newsList.value = articles
                    isLoading.value = false
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message
                    isLoading.value = false
                }
            }
        }
    }
}