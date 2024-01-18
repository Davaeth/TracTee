package com.davaeth.tractee.utils

import java.util.Timer
import kotlin.concurrent.timerTask
import kotlin.time.Duration
import kotlin.time.DurationUnit

class ReschedulingTimer : ExceptedReschedulingTimer<Timer> {
    override var timer: Timer? = null

    override fun schedule(period: Duration, action: () -> Unit) {
        timer?.let { return }

        timer = Timer()
        timer?.schedule(
            /* task = */ timerTask { action() },
            /* delay = */ 0L,
            /* period = */ period.toLong(DurationUnit.MILLISECONDS),
        )
    }

    override fun stop() {
        timer?.cancel()
        timer = null
    }
}