package com.davaeth.tractee

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform