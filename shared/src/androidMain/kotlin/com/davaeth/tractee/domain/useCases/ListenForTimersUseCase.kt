package com.davaeth.tractee.domain.useCases

import com.davaeth.tractee.domain.Mapper
import com.davaeth.tractee.repository.timer.TimerManager
import com.davaeth.tractee.utils.ExpectedReschedulingTimer
import data.TimerEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Timer

class ListenForTimersUseCase(
    private val timerManager: TimerManager,
    private val mapper: Mapper<TimerEntity, ExpectedReschedulingTimer<Timer>>,
) {
    operator fun invoke(): Flow<List<ExpectedReschedulingTimer<Timer>>> =
        timerManager
            .timersListener
            .map { timers -> timers.map { mapper.map(it) } }
}