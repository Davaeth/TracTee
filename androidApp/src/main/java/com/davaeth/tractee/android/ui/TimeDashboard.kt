package com.davaeth.tractee.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.davaeth.tractee.utils.toDisplayable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Stable
class ComposeTimer {
    private var running: Boolean = false

    suspend fun schedule(period: Duration = 1.seconds, action: () -> Unit) {
        running = true
        while (running) {
            delay(period)
            action()
        }
    }

    fun stop() {
        running = false
    }
}

@Composable

fun TimeDashboard() {
    SingleTimer()
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun SingleTimer() {
    val currentTime = remember { mutableStateOf(Duration.ZERO) }
    val currentTimer: ComposeTimer = remember { ComposeTimer() }
    val coroutineScope = rememberCoroutineScope()

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(all = 16.dp)
    ) {
        Text(
            text = currentTime.value.toDisplayable(),
            fontSize = TextUnit(24f, TextUnitType.Sp)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    currentTimer.schedule(1.seconds) {
                        currentTime.value = currentTime.value.plus(1.seconds)
                    }
                }
            },
        ) {
            Text(text = "Start")
        }
        Button(
            onClick = {
                currentTimer.stop()
            },
        ) {
            Text(text = "Stop")
        }
    }
}