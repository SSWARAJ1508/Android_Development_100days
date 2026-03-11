package com.example.cia3.ui.screens

sealed class Screen(val route: String) {

    object Tasks : Screen("tasks")
    object AddTask : Screen("add_task")
    object Manage : Screen("manage")

}