package com.davaeth.tractee.utils

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

interface ExceptedReschedulingTimer<T> {
    var timer: T?

    fun schedule(period: Duration = 1.seconds, action: () -> Unit)

    fun stop()
}