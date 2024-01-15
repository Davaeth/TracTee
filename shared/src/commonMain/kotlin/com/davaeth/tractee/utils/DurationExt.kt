package com.davaeth.tractee.utils

import kotlin.time.Duration

fun Duration.toDisplayable(): String {
    val hours = if (inWholeHours < 10L) "0$inWholeHours" else inWholeHours
    val minutes = if (inWholeMinutes < 10L) "0$inWholeMinutes" else inWholeMinutes
    val seconds = if (inWholeSeconds < 10L) "0$inWholeSeconds" else inWholeSeconds

    return "$hours:$minutes:$seconds"
}