package com.davaeth.tractee.domain.useCases

import com.davaeth.tractee.repository.timer.TimerManager
import com.davaeth.tractee.utils.ExpectedReschedulingTimer
import com.davaeth.tractee.utils.value

class UpdateTimerUseCase(private val timerManager: TimerManager) {
    suspend operator fun invoke(timer: ExpectedReschedulingTimer<*>) = runCatching {
        timerManager.updateTimer(timer.currentTime.value, "", timer.id)
    }
}