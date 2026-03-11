package com.example.cia3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cia3.data.Task
import com.example.cia3.viewmodel.TaskViewModel

@Composable
fun TaskScreen(taskViewModel: TaskViewModel = viewModel()) {

    val tasks by taskViewModel.allTasks.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(tasks) { task ->

            TaskItem(task, taskViewModel)

        }

    }
}

@Composable
fun TaskItem(task: Task, taskViewModel: TaskViewModel) {

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = task.description)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Due: ${task.dueDate}")

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Text("Update")
                }

                Button(
                    onClick = {
                        taskViewModel.delete(task)
                    }
                ) {
                    Text("Delete")
                }

            }

        }

    }

    if (showDialog) {

        UpdateTaskDialog(
            task = task,
            onDismiss = { showDialog = false },
            onUpdate = { updatedTask ->
                taskViewModel.update(updatedTask)
                showDialog = false
            }
        )

    }
}

@Composable
fun UpdateTaskDialog(
    task: Task,
    onDismiss: () -> Unit,
    onUpdate: (Task) -> Unit
) {

    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var dueDate by remember { mutableStateOf(task.dueDate) }

    AlertDialog(

        onDismissRequest = { onDismiss() },

        title = {
            Text("Update Task")
        },

        text = {

            Column {

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due Date") }
                )

            }

        },

        confirmButton = {

            Button(

                onClick = {

                    val updatedTask = task.copy(
                        title = title,
                        description = description,
                        dueDate = dueDate
                    )

                    onUpdate(updatedTask)

                }

            ) {
                Text("Save")
            }

        },

        dismissButton = {

            Button(
                onClick = { onDismiss() }
            ) {
                Text("Cancel")
            }

        }

    )
}