package com.bc.cmpperfbenchmark

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cmpperfbenchmark.composeapp.generated.resources.Res
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        )
    )

    MaterialTheme {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn {
                items(300, key = { it }) {
                    TestRow(angle)
                }
            }
        }
    }
}

@Composable
fun TestRow(angle: Float) {
    LazyRow {
        itemsIndexed(repeatedPictureList, key = { index, drawable ->
            "$drawable-$index"
        }) { index, drawable ->
            Box(modifier = Modifier.size(50.dp)) {
                RotatingImage(drawable, angle)
            }
        }
    }
}

@Composable
fun RotatingImage(drawable: String, angle: Float) {
    AsyncImage(
        model = Res.getUri(drawable),
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .rotate(angle)
    )
}

val pictureList: List<String> = listOf(
    "drawable/image1.jpg",
    "drawable/image2.jpeg",
    "drawable/image3.jpeg",
)

//val pictureDrawable: List<DrawableResource> = listOf(
//    Res.drawable.image1,
//    Res.drawable.image2,
//    Res.drawable.image3,
//)

@Stable
val repeatedPictureList = buildList {
    repeat(10) {
        addAll(pictureList)
    }
}