package com.example.helpconnect.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpconnect.data.MockData
import com.example.helpconnect.ui.components.CustomButton
import com.example.helpconnect.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NgoDetailScreen(
    ngoId: String,
    onNavigateUp: () -> Unit,
    onDonateClick: (String) -> Unit
) {
    val ngo = MockData.ngos.find { it.id == ngoId } ?: return

    Box(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header Image Placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Color.Gray)
            ) {
                // Overlay Gradient
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, DarkBackground),
                                startY = 300f
                            )
                        )
                )
            }

            // Content
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = ngo.name,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = ngo.category,
                            fontSize = 16.sp,
                            color = PrimaryPurple
                        )
                    }
                    // Rating Chip
                    Surface(
                        color = CardBackground,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                            Text("★ ${ngo.rating}", color = Color(0xFFFFD700))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Progress Bar
                val progress = (ngo.raisedAmount / ngo.goalAmount).toFloat()
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = PrimaryPurple,
                    trackColor = CardBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("$${ngo.raisedAmount.toInt()}", color = PrimaryPurple, fontWeight = FontWeight.Bold)
                    Text("Goal: $${ngo.goalAmount.toInt()}", color = TextGrey)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text("About Us", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))
                Text(ngo.description, color = TextGrey, lineHeight = 24.sp)

                Spacer(modifier = Modifier.height(24.dp))

                // Stats Row
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    StatItem("Days Left", "${ngo.daysLeft}")
                    StatItem("Donors", "1.2k")
                    StatItem("Distance", "1.2m")
                }

                Spacer(modifier = Modifier.height(100.dp)) // Padding for bottom button
            }
        }

        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onNavigateUp,
                modifier = Modifier.background(Color.Black.copy(alpha = 0.3f), CircleShape)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            IconButton(
                onClick = { /* Share */ },
                modifier = Modifier.background(Color.Black.copy(alpha = 0.3f), CircleShape)
            ) {
                Icon(Icons.Default.Share, contentDescription = "Share", tint = Color.White)
            }
        }

        // Bottom Donate Button
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(DarkBackground)
                .padding(24.dp)
        ) {
            CustomButton(text = "Donate Now", onClick = { onDonateClick(ngo.id) })
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Text(label, fontSize = 12.sp, color = TextGrey)
    }
}
