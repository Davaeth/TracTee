package com.davaeth.tractee.android.ui.timeDashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.davaeth.tractee.android.R
import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.utils.ExceptedReschedulingTimer
import com.davaeth.tractee.utils.toDisplayable
import org.koin.androidx.compose.koinViewModel
import java.util.Timer

@Composable
fun TimeDashboard(viewModel: TimeDashboardViewModel = koinViewModel()) {
    val stateListener = viewModel.state.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            items(items = stateListener.timers.reversed(), key = { it.id.value }) { timer ->
                SingleTimer(
                    timer = timer,
                    startTimer = viewModel::startTimer,
                    stopTimer = viewModel::stopTimer
                )
            }
        }

        FloatingActionButton(
            onClick = viewModel::addTimer,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add")
        }
    }
}

@Composable
private fun SingleTimer(
    timer: ExceptedReschedulingTimer<Timer>,
    startTimer: (Id) -> Unit,
    stopTimer: (Id) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = timer.currentTime.toDisplayable(),
            fontSize = TextUnit(24f, TextUnitType.Sp)
        )

        IconButton(onClick = { startTimer(timer.id) }, modifier = Modifier.padding(start = 16.dp)) {
            Icon(
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = stringResource(id = R.string.a11y_single_timer_play)
            )
        }
        IconButton(onClick = { stopTimer(timer.id) }) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = stringResource(id = R.string.a11y_single_timer_stop)
            )
        }
    }
}