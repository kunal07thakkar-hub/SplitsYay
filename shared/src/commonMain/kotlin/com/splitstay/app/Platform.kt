package com.splitstay.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform