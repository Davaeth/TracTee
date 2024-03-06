package com.davaeth.tractee.utils

import com.davaeth.tractee.domain.common.Id
import java.util.Timer
import kotlin.concurrent.timerTask
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit

class ReschedulingTimer(override val id: Id) : ExceptedReschedulingTimer<Timer> {
    override var timer: Timer? = null
    override var currentTime: Duration = Duration.ZERO

    override fun schedule(period: Duration, action: (ExceptedReschedulingTimer<Timer>) -> Unit) {
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