package com.example.day_18_advanced_mvvm

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    // Compose-aware mutable list
    private val _tasks = mutableStateListOf<String>()
    val tasks: List<String> get() = _tasks

    fun addTask(task: String) {
        if (task.isNotBlank()) {
            _tasks.add(task)
        }
    }
}
