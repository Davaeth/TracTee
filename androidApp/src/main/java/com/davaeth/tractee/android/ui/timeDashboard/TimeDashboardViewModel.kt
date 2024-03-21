package com.davaeth.tractee.android.ui.timeDashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.domain.useCases.CreateTimerUseCase
import com.davaeth.tractee.domain.useCases.DeleteTimerUseCase
import com.davaeth.tractee.domain.useCases.ListenForTimersUseCase
import com.davaeth.tractee.domain.useCases.UpdateTimerUseCase
import com.davaeth.tractee.utils.ExpectedReschedulingTimer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Timer

class TimeDashboardViewModel(
    private val createTimerUseCase: CreateTimerUseCase,
    private val listenForTimersUseCase: ListenForTimersUseCase,
    private val updateTimerUseCase: UpdateTimerUseCase,
    private val deleteTimerUseCase: DeleteTimerUseCase,
) : ViewModel(), TimeDashboardState {
    private val timers = mutableStateListOf<ExpectedReschedulingTimer<Timer>>()
    private val currentTimer = mutableStateOf<ExpectedReschedulingTimer<Timer>?>(null)

    private val _state = snapshotFlow {
        TimeDashboardState.State(
            timers = timers,
            currentTimer = currentTimer.value
        )
    }
    override val state: StateFlow<TimeDashboardState.State> = _state.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        TimeDashboardState.State.createInitial()
    )

    init {
        viewModelScope.launch {
            listenForTimersUseCase()
                .catch { /* Error handling */ }
                .collectLatest {
                    timers.clear()
                    timers.addAll(it)
                }
        }
    }

    override fun addTimer() {
        viewModelScope.launch {
            createTimerUseCase()
                .onFailure { /* Failure handling */ }
        }
    }

    override fun startTimer(timerId: Id) {
        currentTimer.value?.let { stopTimer(it.id) }
        currentTimer.value = timers.first { it.id == timerId }
        currentTimer.value?.schedule { timer ->
            viewModelScope.launch {
                updateTimerUseCase(timer)
                    .onFailure { /* Failure handling */ }
            }
        }
    }

    override fun stopTimer(timerId: Id) {
        currentTimer.value?.stop()
        currentTimer.value = null
    }

    override fun deleteTimer(timerId: Id) {
        viewModelScope.launch { deleteTimerUseCase(timerId) }
    }
}