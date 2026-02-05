package com.example.day_11_snackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackbarDemoScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackbarDemoScreen() {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var username by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                Snackbar(
                    shape = RoundedCornerShape(16.dp),
                    containerColor = Color(0xFF065F46),
                    contentColor = Color.White
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = snackbarData.visuals.message,
                            modifier = Modifier.weight(1f)
                        )

                        snackbarData.visuals.actionLabel?.let { action ->
                            TextButton(
                                onClick = { snackbarData.performAction() }
                            ) {
                                Text(action, color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF064E3B)),
            contentAlignment = Alignment.Center
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD1FAE5)
                ),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Snackbar Feedback",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF064E3B)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            scope.launch {
                                if (username.isBlank()) {
                                    snackbarHostState.showSnackbar(
                                        message = "Username cannot be empty",
                                        actionLabel = "OK"
                                    )
                                } else {
                                    snackbarHostState.showSnackbar(
                                        message = "Submitted successfully",
                                        actionLabel = "UNDO"
                                    )
                                    username = ""
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF065F46)
                        )
                    ) {
                        Text("Submit", color = Color.White)
                    }
                }
            }
        }
    }
}
