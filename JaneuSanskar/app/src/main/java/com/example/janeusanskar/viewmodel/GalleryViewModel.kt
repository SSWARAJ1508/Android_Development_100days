package com.example.janeusanskar.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.janeusanskar.data.GalleryPost
import com.example.janeusanskar.data.GalleryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {
    private val repository = GalleryRepository()

    // State for the list of gallery items
    private val _galleryItems = MutableStateFlow<List<GalleryPost>>(emptyList())
    val galleryItems: StateFlow<List<GalleryPost>> = _galleryItems.asStateFlow()

    // State to indicate if an upload is in progress
    private val _isUploading = MutableStateFlow(false)
    val isUploading: StateFlow<Boolean> = _isUploading.asStateFlow()

    init {
        fetchGalleryItems()
    }

    private fun fetchGalleryItems() {
        viewModelScope.launch {
            repository.getGalleryItems()
                .catch { exception ->
                    Log.e("GalleryViewModel", "Error fetching gallery items", exception)
                }
                .collect { items ->
                    _galleryItems.value = items
                }
        }
    }

    fun addGalleryItem(imageUri: Uri, description: String) {
        viewModelScope.launch {
            _isUploading.value = true
            try {
                repository.addGalleryItem(imageUri, description)
            } catch (e: Exception) {
                Log.e("GalleryViewModel", "Error adding gallery item", e)
            } finally {
                _isUploading.value = false
            }
        }
    }
}
