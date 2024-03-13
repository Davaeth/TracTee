package com.davaeth.tractee.domain.useCases

import com.davaeth.tractee.domain.Mapper
import com.davaeth.tractee.repository.timer.TimerManager
import com.davaeth.tractee.utils.ExpectedReschedulingTimer
import data.TimerEntity
import java.util.Timer

class RetrieveTimersUseCase(
    private val timerManager: TimerManager,
    private val mapper: Mapper<TimerEntity, ExpectedReschedulingTimer<Timer>>,
) {
    suspend operator fun invoke(): Result<List<ExpectedReschedulingTimer<Timer>>> = runCatching {
        val timers = timerManager.retrieveTimers().getOrThrow()
        timers.map { mapper.map(it) }
    }
}