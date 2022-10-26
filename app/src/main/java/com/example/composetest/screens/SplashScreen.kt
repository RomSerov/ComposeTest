package com.example.composetest.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composetest.MainViewModel
import com.example.composetest.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, viewModel: MainViewModel) {
    //add animation
    var startAnimate by remember { mutableStateOf(false) }
    val alphaAnimation =
        animateFloatAsState(targetValue = if (startAnimate) 1f else 0f, animationSpec = tween(3000))
    LaunchedEffect(key1 = true) {
        startAnimate = true

        //start loading list films
        viewModel.getAllMovies()

        delay(4000)
        navController.navigate(Screens.Main.route)
    }
    Splash(alpha = alphaAnimation.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            imageVector = Icons.Default.PlayArrow, contentDescription = "",
            tint = Color.Black
        )
    }
}