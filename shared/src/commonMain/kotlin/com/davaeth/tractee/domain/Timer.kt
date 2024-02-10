package com.davaeth.tractee.domain

import com.davaeth.tractee.domain.common.Id

data class Timer(
    val id: Id,
    val time: Double,
    val title: String?,
)