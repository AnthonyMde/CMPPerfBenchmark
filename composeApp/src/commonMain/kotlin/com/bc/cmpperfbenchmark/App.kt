package com.bc.cmpperfbenchmark

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import cmpperfbenchmark.composeapp.generated.resources.Res
import cmpperfbenchmark.composeapp.generated.resources.image1
import cmpperfbenchmark.composeapp.generated.resources.image2
import cmpperfbenchmark.composeapp.generated.resources.image3
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn {
                items(300) {
                    TestRow()
                }
            }
        }
    }
}

@Composable
fun TestRow() {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        )
    )
    LazyRow {
        items(repeatedPictureList) { drawable ->
            Box {
                Image(
                    painterResource(drawable),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .rotate(angle)
                )
            }
        }
    }
}

val pictureList: List<DrawableResource> = listOf(
    Res.drawable.image1,
    Res.drawable.image2,
    Res.drawable.image3,
)

val repeatedPictureList = buildList {
    repeat(10) {
        addAll(pictureList)
    }
}

