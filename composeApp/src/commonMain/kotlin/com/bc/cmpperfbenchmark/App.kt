package com.bc.cmpperfbenchmark

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

const val ITEM_COUNT = 4_000
const val TEXT_REFRESH = 8L
const val SCALE_REFRESH = 500
const val ROTATE_REFRESH = 500
const val FONT_SIZE = 16f

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
//            ImageBenchmark()
//            TextBenchmark()
            GridViewBenchmark()
        }
    }
}

@Composable
fun GridViewBenchmark() {
    val time by produceState(initialValue = 0) {
        while (true) {
            delay(TEXT_REFRESH)
            value = Random.nextInt(1, 10_000)
        }
    }
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(ROTATE_REFRESH, easing = LinearEasing),
            RepeatMode.Restart
        )
    )
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            tween(SCALE_REFRESH, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(ITEM_COUNT, key = { it }) { index ->
            val value = remember(time) {
                Random(index + time).nextInt(1, 10_000)
            }
            AnimatedCell(value, rotation, scale)
        }
    }
}

@Composable
fun AnimatedCell(value: Int, rotation: Float, scale: Float) {
    Text(
        text = "Item $value",
        color = Color.White,
        style = TextStyle(
            shadow = Shadow(
                color = Color.Black.copy(alpha = 0.5f),
                offset = Offset(
                    x = 4f * cos(rotation),
                    y = 4f * sin(rotation)
                ),
                blurRadius = 8f
            )
        ),
        textAlign = TextAlign.Center,
        fontSize = (FONT_SIZE * scale).sp,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .graphicsLayer(
//                rotationZ = rotation,
                rotationX = rotation,
                scaleX = scale,
                scaleY = scale,
                shadowElevation = 20f,
                shape = CircleShape,
                clip = true
            )
            .background(Color.Red)
            .padding(16.dp)
    )
}


