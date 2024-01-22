package com.jnsaph.jarvis_update_server.models

import kotlinx.serialization.Serializable

@Serializable
data class VersionData(
    val version: String,
    val notes: String,
    val pub_date: String,
    val platforms: Map<String, PlatformInfo>
)

@Serializable
data class PlatformInfo(
    val signature: String,
    val url: String
)
