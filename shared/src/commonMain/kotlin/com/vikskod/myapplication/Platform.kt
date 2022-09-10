package com.vikskod.myapplication

import org.koin.core.module.Module

expect class Platform() {
    val platform: String
}

expect fun platformModule(): Module