package com.jnsaph.jarvis_update_server.routes.api

import com.jnsaph.jarvis_update_server.db.PostgreSQL
import com.jnsaph.jarvis_update_server.models.FeedbackData
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

import com.jnsaph.jarvis_update_server.models.ResponseData
import io.ktor.http.*

//val envMongoDBConectionString: String = System.getenv("MONGODB_CONNECTION_STRING")
//val envMongoDBDBName: String = System.getenv("MONGODB_DB_NAME")
//val envMongoDBCollectionName: String = System.getenv("MONGODB_COLLECTION_NAME")

fun Route.postFeedbackRoute() {
    post("/api/feedback") {
        try {
            // Get feedback data from request body
            val feedbackData = call.receive<FeedbackData>()

            // For now respond with error maintenance
            call.respond(HttpStatusCode.ServiceUnavailable, ResponseData("error", "Currently down for maintenance"))

            // Check if feedback data is valid
            if (isValidFeedbackData(feedbackData)) {
                call.respond(HttpStatusCode.OK, ResponseData("success", "Feedback received"))
            } else {
                call.respond(HttpStatusCode.BadRequest, ResponseData("error", "Invalid feedback data"))
            }

            // Success response
            call.respond(ResponseData("success", "Feedback received"))
        } catch (e: ContentTransformationException) {
            // Invalid feedback data
            call.respond(ResponseData("error", "Invalid feedback data"))
        } catch (e: Exception) {
            // Internal server error
            call.respond(ResponseData("error", "Internal server error"))
        } finally {
            // Disconnect from database
        }
    }
}

fun isValidFeedbackData(feedbackData: FeedbackData): Boolean {
    return feedbackData.feedbackEmoji.isNotEmpty() &&
            feedbackData.feedbackLike.isNotEmpty() &&
            feedbackData.feedbackDislike.isNotEmpty()
}