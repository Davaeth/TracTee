package com.davaeth.tractee.domain.useCases

import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.repository.timer.TimerManager

class DeleteTimerUseCase(private val timerManager: TimerManager) {
    suspend operator fun invoke(id: Id): Result<Unit> =
        runCatching { timerManager.deleteTimer(id) }
}