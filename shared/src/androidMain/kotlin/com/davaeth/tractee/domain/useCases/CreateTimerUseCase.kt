package com.davaeth.tractee.domain.useCases

import com.davaeth.tractee.repository.timer.TimerManager

class CreateTimerUseCase(private val timeManager: TimerManager) {
    suspend operator fun invoke(): Result<Unit> = runCatching { timeManager.insertTimer() }
}