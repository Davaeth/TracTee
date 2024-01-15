package com.davaeth.tractee.domain.simple

import kotlin.jvm.JvmInline

@JvmInline
value class Id(val value: Long) {
    init {
        require(value >= 0) { "Id cannot be null!" }
    }
}