package com.example.day_12_animated_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedAnimatedUI()
        }
    }
}

@Composable
fun AdvancedAnimatedUI() {

    var expanded by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = spring(),
        label = "rotation"
    )

    val scale by animateFloatAsState(
        targetValue = if (expanded) 1.05f else 1f,
        animationSpec = tween(400),
        label = "scale"
    )

    val elevation by animateDpAsState(
        targetValue = if (expanded) 24.dp else 8.dp,
        label = "elevation"
    )

    val cardColor by animateColorAsState(
        targetValue = if (expanded) Color(0xFF2563EB) else Color(0xFFDC2626),
        label = "color"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color.Black, Color.DarkGray)
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .scale(scale)
                .clickable { expanded = !expanded },
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(elevation),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {

            Column(modifier = Modifier.padding(24.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Advanced Animated UI",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.rotate(rotation)
                    )
                }

                AnimatedVisibility(
                    visible = expanded,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column(modifier = Modifier.padding(top = 20.dp)) {

                        Text(
                            text = "Complex animations using scale, color, rotation & visibility.",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = { expanded = false },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Collapse", color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}
