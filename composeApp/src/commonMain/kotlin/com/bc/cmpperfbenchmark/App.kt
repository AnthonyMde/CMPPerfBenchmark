package com.bc.cmpperfbenchmark

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
//            ImageBenchmark()
            TextBenchmark()
        }
    }
}

@Composable
fun TextBenchmark() {
    val infiniteTransition = rememberInfiniteTransition()
    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    LazyColumn {
        items(3000, key = { it }) {
            TextRow(animatedAlpha)
        }
    }
}

@Composable
fun TextRow(alpha: Float) {
    LazyRow{
        items(count = 1, key = { "unique key" }) { index ->
            TextTest(alpha)
        }
    }

}

@OptIn(ExperimentalTime::class)
@Composable
fun TextTest(alpha: Float) {
    val time by produceState(initialValue = "", producer = {
        while (true) {
            value = Clock.System.now().toString()
            delay(16)
        }
    })

    Text(
        text = "time: $time",
        modifier = Modifier
            .shadow(8.dp, ambientColor = Color.Black.copy(alpha = 1f - alpha))
            .graphicsLayer(alpha = alpha)
    )
}