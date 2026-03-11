package com.example.cia3

import android.os.Bundle
import androidx.compose.foundation.layout.padding
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.cia3.ui.screens.*

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            Scaffold(

                topBar = {
                    TopAppBar(
                        title = { Text("Task Manager") },
                        actions = {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Settings"
                                )
                            }
                        }
                    )
                },

                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(Screen.AddTask.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Task"
                        )
                    }
                },

                bottomBar = {
                    BottomNavigationBar(navController)
                }

            ) { innerPadding ->

                NavHost(
                    navController = navController,
                    startDestination = Screen.Tasks.route,
                    modifier = Modifier.padding(innerPadding)
                ) {

                    composable(Screen.Tasks.route) {
                        TaskScreen()
                    }

                    composable(Screen.AddTask.route) {
                        AddTaskScreen(navController)
                    }

                    composable(Screen.Manage.route) {
                        ManageScreen()
                    }

                }
            }
        }
    }
}