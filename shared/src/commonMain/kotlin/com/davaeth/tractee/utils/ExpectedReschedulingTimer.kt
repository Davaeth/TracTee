package com.davaeth.tractee.utils

import com.davaeth.tractee.domain.common.Id
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

interface ExpectedReschedulingTimer<T> {
    var timer: T?
    val id: Id
    val currentTime: Duration

    fun schedule(period: Duration = 1.seconds, action: (ExpectedReschedulingTimer<T>) -> Unit)

    fun stop()
}