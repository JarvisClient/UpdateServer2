package com.jnsaph.jarvis_update_server.models

import kotlinx.serialization.Serializable

@Serializable
data class FeedbackData(
    val feedbackEmoji: String,
    val feedbackLike: String,
    val feedbackDislike: String
)