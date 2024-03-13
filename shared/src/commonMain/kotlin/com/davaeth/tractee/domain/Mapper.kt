package com.davaeth.tractee.domain

fun interface Mapper<in Input, out Output> {
    fun map(input: Input): Output
}