package com.bc.cmpperfbenchmark.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import cmpperfbenchmark.composeapp.generated.resources.Res
import coil3.compose.AsyncImage
import com.bc.cmpperfbenchmark.anim.infiniteRotatingAnim
//import com.github.panpf.sketch.AsyncImage
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ScrollingListPerf() {
    val images = remember {
        listOf(
            "drawable/image1.jpg",
            "drawable/image2.jpeg",
            "drawable/image3.jpeg",
        )
    }

    val rowImages = remember {
        List(30) { index -> images[index % images.size] }
    }

    // Single shared rotation animation state for all images
    val rotation by infiniteRotatingAnim()

    LazyColumn {
        items(300) { _ ->
            LazyRow {
                items(rowImages) { imagePath ->
                    RotatingImage(
                        imagePath = imagePath,
                        rotation = rotation
                    )
                }
            }
        }
    }
}

@Composable
private fun RotatingImage(
    imagePath: String,
    rotation: Float
) {
    Box(modifier = Modifier.size(50.dp)) {
        AsyncImage(
            model = Res.getUri(imagePath),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .rotate(rotation)
        )
    }
}