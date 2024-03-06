package com.davaeth.tractee.android.ui.timeDashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.utils.ExceptedReschedulingTimer
import com.davaeth.tractee.utils.ReschedulingTimer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.util.Timer

class TimeDashboardViewModel : ViewModel(), TimeDashboardState {
    private val timers = mutableStateListOf<ExceptedReschedulingTimer<Timer>>()

    private val _state = snapshotFlow { TimeDashboardState.State(timers = timers) }
    override val state: StateFlow<TimeDashboardState.State> = _state.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        TimeDashboardState.State.createInitial()
    )

    override fun addTimer() {
        timers.add(ReschedulingTimer(Id(timers.lastIndex + 1L)))
    }

    override fun startTimer(timerId: Id) {
        timers.first { it.id == timerId }
            .schedule { timer ->
                timers[timers.indexOfFirst { it.id == timerId }] = timer
            }
    }

    override fun stopTimer(timerId: Id) {
        timers.first { it.id == timerId }.stop()
    }

    override fun deleteTimer(timerId: Id) {
        timers.removeIf { it.id == timerId }
    }
}