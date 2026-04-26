package com.example.android_labs

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform