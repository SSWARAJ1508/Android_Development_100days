package com.example.day_10_formvalidation

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormValidationScreen()
        }
    }
}

@Composable
fun FormValidationScreen() {

    // Input states
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    // Error states
    var nameError by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var ageError by remember { mutableStateOf("") }
    var genderError by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF064E3B)), // Dark Green background
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFD1FAE5) // Light Green card
            ),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "User Registration",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF064E3B)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Name
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = ""
                    },
                    label = { Text("Name") },
                    isError = nameError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (nameError.isNotEmpty()) {
                    Text(nameError, color = Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Username
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        usernameError = ""
                    },
                    label = { Text("Username") },
                    isError = usernameError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (usernameError.isNotEmpty()) {
                    Text(usernameError, color = Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Password
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = ""
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = passwordError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (passwordError.isNotEmpty()) {
                    Text(passwordError, color = Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Age
                OutlinedTextField(
                    value = age,
                    onValueChange = {
                        age = it
                        ageError = ""
                    },
                    label = { Text("Age") },
                    isError = ageError.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )
                if (ageError.isNotEmpty()) {
                    Text(ageError, color = Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Gender
                Text(
                    text = "Gender",
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF064E3B)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = gender == "Male",
                        onClick = {
                            gender = "Male"
                            genderError = ""
                        }
                    )
                    Text("Male")

                    Spacer(modifier = Modifier.width(12.dp))

                    RadioButton(
                        selected = gender == "Female",
                        onClick = {
                            gender = "Female"
                            genderError = ""
                        }
                    )
                    Text("Female")
                }

                if (genderError.isNotEmpty()) {
                    Text(genderError, color = Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Submit Button
                Button(
                    onClick = {
                        var valid = true

                        if (name.isBlank()) {
                            nameError = "Name cannot be empty"
                            valid = false
                        }

                        if (username.length < 4) {
                            usernameError = "Username must be at least 4 characters"
                            valid = false
                        }

                        if (password.length < 6) {
                            passwordError = "Password must be at least 6 characters"
                            valid = false
                        }

                        val ageInt = age.toIntOrNull()
                        if (ageInt == null || ageInt < 18) {
                            ageError = "Age must be 18 or above"
                            valid = false
                        }

                        if (gender.isBlank()) {
                            genderError = "Please select gender"
                            valid = false
                        }

                        if (valid) {
                            // Success action
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
