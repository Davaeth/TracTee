package com.davaeth.tractee.android.ui.timeDashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.domain.useCases.CreateTimerUseCase
import com.davaeth.tractee.utils.ExceptedReschedulingTimer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Timer

class TimeDashboardViewModel(private val createTimerUseCase: CreateTimerUseCase) : ViewModel(),
    TimeDashboardState {
    private val timers = mutableStateListOf<ExceptedReschedulingTimer<Timer>>()

    private val _state = snapshotFlow { TimeDashboardState.State(timers = timers) }
    override val state: StateFlow<TimeDashboardState.State> = _state.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        TimeDashboardState.State.createInitial()
    )

    override fun addTimer() {
        viewModelScope.launch {
            createTimerUseCase()
                .onSuccess { timers.add(it) }
                .onFailure { /* Failure handling */ }
        }
    }

    override fun startTimer(timerId: Id) {
        timers
            .first { it.id == timerId }
            .schedule { timer -> timers.update(timerId, timer) }
    }

    override fun stopTimer(timerId: Id) {
        timers.first { it.id == timerId }.stop()
    }

    override fun deleteTimer(timerId: Id) {
        timers.removeIf { it.id == timerId }
    }
}

private fun SnapshotStateList<ExceptedReschedulingTimer<Timer>>.update(
    idToChange: Id,
    newItem: ExceptedReschedulingTimer<Timer>,
) {
    val index = indexOfFirst { it.id == idToChange }
    removeAt(index)
    add(index, newItem)
}
