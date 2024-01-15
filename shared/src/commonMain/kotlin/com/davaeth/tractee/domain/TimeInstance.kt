package com.davaeth.tractee.domain

import com.davaeth.tractee.domain.simple.Id

data class TimeInstance(
    val id: Id,
    val time: Double,
    val title: String?,
)
