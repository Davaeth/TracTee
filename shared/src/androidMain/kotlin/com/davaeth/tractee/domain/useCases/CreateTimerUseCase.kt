package com.davaeth.tractee.domain.useCases

import com.davaeth.tractee.domain.ReschedulingTimer
import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.repository.timer.TimerManager
import com.davaeth.tractee.utils.toDuration

class CreateTimerUseCase(private val timeManager: TimerManager) {
    suspend operator fun invoke(): Result<ReschedulingTimer> = runCatching {
        timeManager.insertTimer()
        timeManager.getNewestTimer()
            .getOrThrow()
            .let {
                ReschedulingTimer(
                    Id(it.id),
                    it.time.toDuration(),
                )
            }
    }
}