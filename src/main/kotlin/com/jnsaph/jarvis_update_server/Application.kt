package com.jnsaph.jarvis_update_server

import com.jnsaph.jarvis_update_server.plugins.configureJSON
import com.jnsaph.jarvis_update_server.routes.api.postFeedbackRoute
import com.jnsaph.plugins.*

// Ktor
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

// Routes
import com.jnsaph.jarvis_update_server.routes.getVersionRoute
import com.jnsaph.jarvis_update_server.routes.validateEnvironmentVariables
import io.ktor.server.routing.*

fun main() {
    validateEnvironmentVariables()
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.configureRouting() {
    routing {
        getVersionRoute()
        postFeedbackRoute()
    }
}
fun Application.module() {
    configureHTTP()
    configureRouting()
    configureJSON()
}
