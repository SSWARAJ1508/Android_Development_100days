package com.example.cia3.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Tasks.route) },
            icon = {
                Icon(Icons.Filled.List, contentDescription = "Tasks")
            },
            label = {
                Text("Tasks")
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Screen.Manage.route) },
            icon = {
                Icon(Icons.Filled.Settings, contentDescription = "Manage")
            },
            label = {
                Text("Manage")
            }
        )
    }
}