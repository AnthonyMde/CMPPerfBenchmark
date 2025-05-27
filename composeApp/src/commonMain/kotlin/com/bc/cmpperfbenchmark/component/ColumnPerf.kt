package com.bc.cmpperfbenchmark.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import cmpperfbenchmark.composeapp.generated.resources.Res
import cmpperfbenchmark.composeapp.generated.resources.image1
import cmpperfbenchmark.composeapp.generated.resources.image2
import cmpperfbenchmark.composeapp.generated.resources.image3
import com.bc.cmpperfbenchmark.anim.infiniteRotatingAnim
import org.jetbrains.compose.resources.painterResource

const val NUMBER_OF_ROWS = 100

val images = listOf(
    Res.drawable.image1,
    Res.drawable.image2,
    Res.drawable.image3,
    Res.drawable.image1,
    Res.drawable.image2,
)

@Composable
fun ColumnPerf() {
    repeat(NUMBER_OF_ROWS) {
        RowImages()
    }
}

@Composable
fun RowImages() {
    val rotation by infiniteRotatingAnim()
    Row {
        images.forEach { drawable ->
            Box {
                Image(
                    painterResource(drawable),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .rotate(rotation)
                )
            }
        }
    }
}