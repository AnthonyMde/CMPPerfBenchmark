import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

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