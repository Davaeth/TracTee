package com.davaeth.tractee.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.davaeth.tractee.utils.toDisplayable
import java.time.Instant
import java.util.Date
import java.util.Timer
import kotlin.concurrent.timer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

@Composable

fun TimeDashboard() {
    SingleTimer()
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun SingleTimer() {
    val currentTime = remember { mutableStateOf(Duration.ZERO) }
    var currentTimer: Timer? = null

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
                currentTimer = timer(
                    startAt = Date.from(Instant.now()),
                    period = 1.seconds.toLong(DurationUnit.MILLISECONDS),
                    action = { currentTime.value = currentTime.value.plus(1.seconds) })
            },
        ) {
            Text(text = "Start")
        }
        Button(
            onClick = {
                currentTimer?.purge()
                currentTimer?.cancel()
                currentTimer = null
            },
        ) {
            Text(text = "Stop")
        }
    }
}