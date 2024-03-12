package com.davaeth.tractee.android.ui.timeDashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.davaeth.tractee.android.ui.base.ComposeState
import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.utils.ExpectedReschedulingTimer
import java.util.Timer

interface TimeDashboardState : ComposeState<TimeDashboardState.State> {
    data class State(val timers: SnapshotStateList<ExpectedReschedulingTimer<Timer>>) {
        companion object {
            fun createInitial() = State(mutableStateListOf())
        }
    }

    fun addTimer()
    fun startTimer(timerId: Id)
    fun stopTimer(timerId: Id)
    fun deleteTimer(timerId: Id)
}