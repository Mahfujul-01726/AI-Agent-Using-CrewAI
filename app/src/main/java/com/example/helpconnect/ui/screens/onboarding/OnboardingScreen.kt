package com.example.helpconnect.ui.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helpconnect.ui.components.CustomButton
import com.example.helpconnect.ui.components.ScreenBackground
import com.example.helpconnect.ui.theme.PrimaryPurple
import com.example.helpconnect.ui.theme.TextGrey

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onNavigateToLogin: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val pages = listOf(
        OnboardingPage(
            title = "Empower Change",
            description = "Join a global community dedicated to making a positive impact."
        ),
        OnboardingPage(
            title = "Connect & Donate",
            description = "Find causes you care about and donate securely with just a few taps."
        ),
        OnboardingPage(
            title = "Ready to Make a Difference?",
            description = "Tap below to explore causes, connect with communities, and start your giving journey."
        )
    )

    ScreenBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Illustration Placeholder
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(vertical = 40.dp)
                    .background(Color.White.copy(alpha = 0.1f), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("Illustration ${pagerState.currentPage + 1}", color = Color.White)
            }

            // Pager Content
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(0.5f)
            ) { page ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = pages[page].title,
                        fontSize = 28.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = pages[page].description,
                        fontSize = 16.sp,
                        color = TextGrey,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Indicators
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) PrimaryPurple else Color.Gray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(8.dp)
                            .background(color, CircleShape)
                    )
                }
            }

            // Button (only on last page or all? Image shows "Get Started" on last)
            if (pagerState.currentPage == 2) {
                CustomButton(text = "Get Started", onClick = onNavigateToLogin)
            } else {
                Spacer(modifier = Modifier.height(56.dp))
            }
        }
    }
}

data class OnboardingPage(val title: String, val description: String)
