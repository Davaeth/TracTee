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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.utils.ExceptedReschedulingTimer
import com.davaeth.tractee.utils.toDisplayable
import org.koin.androidx.compose.koinViewModel
import java.util.Timer

@Composable
fun TimeDashboard(viewModel: TimeDashboardViewModel = koinViewModel()) {
    val stateListener = viewModel.state.collectAsState().value

    if (stateListener.timers.isEmpty()) viewModel.addTimer()

    stateListener.timers.forEach { timer ->
        SingleTimer(
            timer = timer,
            startTimer = viewModel::startTimer,
            stopTimer = viewModel::stopTimer
        )
    }
}

@Composable
private fun SingleTimer(
    timer: ExceptedReschedulingTimer<Timer>,
    startTimer: (Id) -> Unit,
    stopTimer: (Id) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(all = 16.dp)
    ) {
        Text(
            text = timer.currentTime.toDisplayable(),
            fontSize = TextUnit(24f, TextUnitType.Sp)
        )

        Button(onClick = { startTimer(timer.id) }) {
            Text(text = "Start")
        }
        Button(onClick = { stopTimer(timer.id) }) {
            Text(text = "Stop")
        }
    }
}