package com.davaeth.tractee.domain.common

import kotlin.jvm.JvmInline

@JvmInline
value class Id(val value: Long) {
    init {
        require(value >= 0) { "Id cannot be null!" }
    }
}

val Id.databaseValue
    get() = value.toString()