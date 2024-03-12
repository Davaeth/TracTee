package com.davaeth.tractee.domain

import com.davaeth.tractee.domain.common.Id
import com.davaeth.tractee.utils.ExpectedReschedulingTimer
import java.util.Timer
import kotlin.concurrent.timerTask
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

class ReschedulingTimer(id: Id, currentTime: Duration) : ExpectedReschedulingTimer<Timer> {
    override val id: Id
    override var timer: Timer? = null
    override var currentTime: Duration = Duration.ZERO

    init {
        this.id = id
        this.currentTime = currentTime
    }

    override fun schedule(period: Duration, action: (ExpectedReschedulingTimer<Timer>) -> Unit) {
        timer?.let { return }

        timer = Timer()
        timer?.schedule(
            /* task = */
            timerTask {
                currentTime = currentTime.plus(1.seconds)
                action(this@ReschedulingTimer)
            },
            /* delay = */ 0L,
            /* period = */ period.toLong(DurationUnit.MILLISECONDS),
        )
    }

    override fun stop() {
        timer?.cancel()
        timer = null
    }
}