package com.jnsaph.jarvis_update_server.models

import kotlinx.serialization.Serializable

@Serializable
data class ResponseData(
    val status: String,  // "success" or "error"
    val message: String, // Detailed message
)
