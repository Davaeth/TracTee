package com.davaeth.tractee.android.ui.timeDashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.davaeth.tractee.utils.toDisplayable
import org.koin.androidx.compose.koinViewModel

@Composable
fun TimeDashboard() {
    val viewModel: TimeDashboardViewModel = koinViewModel()
    with(viewModel) {
        SingleTimer()
    }
}

context (TimeDashboardState)
@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun SingleTimer() {
    val stateListener = state.collectAsState().value

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(all = 16.dp)
    ) {
        Text(
            text = stateListener.timers.first().currentTime.toDisplayable(),
            fontSize = TextUnit(24f, TextUnitType.Sp)
        )

        Button(onClick = { startTimer(stateListener.timers.first().id) }) {
            Text(text = "Start")
        }
        Button(onClick = { stopTimer(stateListener.timers.first().id) }) {
            Text(text = "Stop")
        }
    }
}