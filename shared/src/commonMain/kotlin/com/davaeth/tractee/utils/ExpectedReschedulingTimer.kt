package com.davaeth.tractee.utils

import com.davaeth.tractee.domain.common.Id
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

abstract class ExpectedReschedulingTimer<T> {
    protected abstract var timer: T?
    abstract val id: Id
    abstract val currentTime: Duration

    abstract fun schedule(
        period: Duration = 1.seconds,
        action: (ExpectedReschedulingTimer<T>) -> Unit,
    )

    abstract fun stop()
}