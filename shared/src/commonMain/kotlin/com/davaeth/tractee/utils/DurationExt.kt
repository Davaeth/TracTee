package com.davaeth.tractee.utils

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun Duration.toDisplayable(): String {
    val hours = if (inWholeHours < 10L) "0$inWholeHours" else inWholeHours
    val minutes = if (inWholeMinutes < 10L) "0$inWholeMinutes" else inWholeMinutes
    val seconds = if (inWholeSeconds < 10L) "0$inWholeSeconds" else inWholeSeconds

    return "$hours:$minutes:$seconds"
}

fun Long.toDuration() = toDuration(DurationUnit.MILLISECONDS)

val Duration.value
    get() = inWholeMilliseconds