package com.example.sw_univ_hackathon.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sw_univ_hackathon.ui.theme.MDPoint

@Composable
fun ShowProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = 0f))
    ) {
        CircularProgressIndicator(
            color = MDPoint,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}