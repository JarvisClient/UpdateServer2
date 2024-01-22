package com.jnsaph.jarvis_update_server.plugins

import io.ktor.server.application.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureJSON() {
    install(ContentNegotiation) {
        json()
    }
}