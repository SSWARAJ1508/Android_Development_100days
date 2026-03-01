package com.example.janeusanskar.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.janeusanskar.ui.theme.DeepSaffron
import com.example.janeusanskar.ui.theme.JaneuSanskarTheme
import com.example.janeusanskar.ui.theme.Maroon

@Composable
fun InvitationScreen(
    onDownloadClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("Invitation Image Placeholder", style = MaterialTheme.typography.titleLarge)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onDownloadClick,
                colors = ButtonDefaults.buttonColors(containerColor = DeepSaffron)
            ) {
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = "Download Invitation",
                    tint = Maroon
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Download", color = Maroon)
            }

            Button(
                onClick = onShareClick,
                colors = ButtonDefaults.buttonColors(containerColor = DeepSaffron)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share on WhatsApp",
                    tint = Maroon
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Share", color = Maroon)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun InvitationScreenPreview() {
    JaneuSanskarTheme {
        InvitationScreen(onDownloadClick = {}, onShareClick = {})
    }
}
