package com.example.helpconnect.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpconnect.data.MockData
import com.example.helpconnect.ui.components.ScreenBackground
import com.example.helpconnect.ui.theme.CardBackground
import com.example.helpconnect.ui.theme.TextGrey

@Composable
fun ProfileScreen(onLogout: () -> Unit) {
    val user = MockData.currentUser

    ScreenBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Gray, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(user.name.first().toString(), fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(user.name, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(user.email, color = TextGrey)
                }
            }

            // Menu Items
            ProfileMenuItem(icon = Icons.Default.History, title = "My Donations", onClick = {})
            ProfileMenuItem(icon = Icons.Default.Person, title = "Edit Profile", onClick = {})
            ProfileMenuItem(icon = Icons.Default.Settings, title = "Settings", onClick = {})

            Spacer(modifier = Modifier.height(32.dp))
            Divider(color = CardBackground)
            Spacer(modifier = Modifier.height(32.dp))

            ProfileMenuItem(icon = Icons.Default.Logout, title = "Log Out", onClick = onLogout, isDestructive = true)
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, title: String, onClick: () -> Unit, isDestructive: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isDestructive) Color.Red else Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                color = if (isDestructive) Color.Red else Color.White
            )
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = TextGrey
        )
    }
}
