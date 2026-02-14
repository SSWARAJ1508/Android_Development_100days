package com.example.day_19_bottomsheet

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
            BottomSheetDesigned()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDesigned() {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    // Colors
    val brown = Color(0xFF6D4C41)
    val lightYellow = Color(0xFFFFF8E1)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightYellow),
        contentAlignment = Alignment.Center
    ) {

        Button(
            onClick = { showSheet = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = brown
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Open Bottom Sheet", color = Color.White)
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState,
                containerColor = lightYellow,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    // Title
                    Text(
                        text = "Choose an Option",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = brown
                    )

                    Divider(color = brown.copy(alpha = 0.3f))

                    // Options
                    SheetOption("Add to Cart", brown) { showSheet = false }
                    SheetOption("Save for Later", brown) { showSheet = false }
                    SheetOption("Share Item", brown) { showSheet = false }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Close Button
                    Button(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                                showSheet = false
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = brown
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Close", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun SheetOption(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = color
            )
        }
    }
}
