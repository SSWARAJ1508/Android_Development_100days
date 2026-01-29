package com.example.day_04_splashscreen

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.sin

class MainActivity : ComponentActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ NEW sound file name → no cache issue
        mediaPlayer = MediaPlayer.create(this, R.raw.taskbug_splash_final)
        mediaPlayer?.start()

        setContent {
            SplashScreen()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

@Composable
fun SplashScreen() {

    val fullText = "Taskbug"
    var visibleText by remember { mutableStateOf("") }

    val infiniteTransition = rememberInfiniteTransition()

    // Center oscillation (NO DRIFT)
    val bugX by infiniteTransition.animateFloat(
        initialValue = -12f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    val bugY by infiniteTransition.animateFloat(
        initialValue = -6f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Background pulse
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.96f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400),
            repeatMode = RepeatMode.Reverse
        )
    )

    val backgroundColor by animateColorAsState(
        targetValue = Color(0xFFF8FAFC),
        animationSpec = tween(1000)
    )

    // Typing illusion
    LaunchedEffect(Unit) {
        fullText.forEachIndexed { index, _ ->
            visibleText = fullText.take(index + 1)
            delay(350)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor.copy(alpha = pulseAlpha)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.size(140.dp),
                contentAlignment = Alignment.Center
            ) {

                // 🐞 BUG ICON — PERFECTLY CENTERED
                Image(
                    painter = painterResource(id = R.drawable.ic_taskbug),
                    contentDescription = "Taskbug Icon",
                    modifier = Modifier
                        .size(56.dp)
                        .offset {
                            IntOffset(
                                x = bugX.toInt(),
                                y = bugY.toInt()
                            )
                        }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = visibleText,
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF0F172A)
            )
        }
    }
}
