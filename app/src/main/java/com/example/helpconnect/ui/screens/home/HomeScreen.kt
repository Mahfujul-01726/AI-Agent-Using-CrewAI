package com.example.helpconnect.ui.screens.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpconnect.data.MockData
import com.example.helpconnect.model.Ngo
import com.example.helpconnect.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNgoClick: (String) -> Unit,
    onMenuClick: () -> Unit
) {
    val ngos = MockData.ngos

    Box(modifier = Modifier.fillMaxSize().background(DarkBackground)) {
        // 1. Map Simulation Layer
        MapSimulation(ngos)

        // 2. Top Bar / Search
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "NGO Locator",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = onMenuClick,
                    modifier = Modifier.background(CardBackground, CircleShape)
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search by name or location", color = TextGrey) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextGrey) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = CardBackground,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        // 3. Floating Action Buttons (Filter, MyLocation, List)
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp, bottom = 250.dp) // Lifted above the bottom card
        ) {
            FloatingActionButton(
                onClick = {},
                containerColor = PrimaryPurple,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(Icons.Default.FilterList, contentDescription = "Filter")
            }
            FloatingActionButton(
                onClick = {},
                containerColor = PrimaryBlue,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.MyLocation, contentDescription = "Location")
            }
        }

        // 4. Bottom Sheet / NGO Preview Card (Mocking the "Paws & Whiskers" card)
        val selectedNgo = ngos.first()
        NgoPreviewCard(
            ngo = selectedNgo,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            onClick = { onNgoClick(selectedNgo.id) }
        )
    }
}

@Composable
fun MapSimulation(ngos: List<Ngo>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        // Draw Grid Lines (Map feel)
        val gridSize = 100f
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

        for (i in 0 until (width / gridSize).toInt()) {
            drawLine(
                color = Color.White.copy(alpha = 0.05f),
                start = Offset(i * gridSize, 0f),
                end = Offset(i * gridSize, height),
                pathEffect = pathEffect
            )
        }
        for (i in 0 until (height / gridSize).toInt()) {
            drawLine(
                color = Color.White.copy(alpha = 0.05f),
                start = Offset(0f, i * gridSize),
                end = Offset(width, i * gridSize),
                pathEffect = pathEffect
            )
        }

        // Draw "Streets" (Arbitrary lines)
        drawLine(
            color = Color.White.copy(alpha = 0.1f),
            start = Offset(0f, height * 0.3f),
            end = Offset(width, height * 0.4f),
            strokeWidth = 5f
        )
        drawLine(
            color = Color.White.copy(alpha = 0.1f),
            start = Offset(width * 0.2f, 0f),
            end = Offset(width * 0.8f, height),
            strokeWidth = 5f
        )

        // Draw NGO Pins
        // Very basic projection simulation
        // Lat: 40.71 -> y, Lng: -74.00 -> x
        // We'll just scatter them based on index for the mock
        ngos.forEachIndexed { index, _ ->
            val x = width * (0.2f + (index * 0.2f) % 0.6f)
            val y = height * (0.3f + (index * 0.15f) % 0.5f)

            drawCircle(
                color = PrimaryPurple.copy(alpha = 0.3f),
                radius = 40f,
                center = Offset(x, y)
            )
            drawCircle(
                color = PrimaryPurple,
                radius = 15f,
                center = Offset(x, y)
            )
        }
    }
}

@Composable
fun NgoPreviewCard(ngo: Ngo, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Placeholder Image
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray, RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = ngo.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = ngo.category,
                    fontSize = 14.sp,
                    color = TextGrey
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = ngo.distance, fontSize = 12.sp, color = TextGrey)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "★ ${ngo.rating}", fontSize = 12.sp, color = Color(0xFFFFD700))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
             Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier.weight(1f)
            ) {
                 Text("View Details", color = Color.White)
            }

            Button(
                onClick = onClick, // Navigate to details first usually, or direct donate
                colors = ButtonDefaults.buttonColors(
                    containerColor = Brush.horizontalGradient(
                        colors = listOf(PrimaryPurple, PrimaryBlue)
                    ).let { Color.Transparent } // Compose button background gradient hack is harder, using solid for now
                ),
                modifier = Modifier
                    .weight(1f)
                    .background(
                        brush = Brush.horizontalGradient(listOf(PrimaryPurple, PrimaryBlue)),
                        shape = RoundedCornerShape(20.dp)
                    )
            ) {
                Text("Donate", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
