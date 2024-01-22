package com.jnsaph.jarvis_update_server.routes

import com.jnsaph.jarvis_update_server.fetch.GitHubAPI
import com.jnsaph.jarvis_update_server.models.PlatformInfo
import com.jnsaph.jarvis_update_server.models.ResponseData
import com.jnsaph.jarvis_update_server.models.VersionData
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlin.system.exitProcess

// Environment variables with default values
val envGithubAPIUsername: String = System.getenv("GITHUB_API_USERNAME") ?: ""
val envGithubAPIRepository: String = System.getenv("GITHUB_API_REPOSITORY") ?: ""
val envGithubAPIToken: String = System.getenv("GITHUB_API_TOKEN") ?: ""

// Validation function
fun validateEnvironmentVariables() {
    if (envGithubAPIUsername.isEmpty() || envGithubAPIRepository.isEmpty() || envGithubAPIToken.isEmpty()) {
        System.err.println("One or more environment variables are not set.")
        exitProcess(1)
    }
}


// Instance of GitHubAPI
val GithubAPI = GitHubAPI(
    username = envGithubAPIUsername,
    repository = envGithubAPIRepository,
    accessToken = envGithubAPIToken
)

fun Route.getVersionRoute() {
    get("/latest") {
        try {
            val response = GithubAPI.getLatestRelease()

            val platformDownloadLinks = mutableMapOf<String, PlatformInfo>()

            for (asset in response.assets) {
                val platform = when {
                    asset.name.contains("windows", true) -> "windows"
                    asset.name.contains("mac", true) -> "mac"
                    asset.name.contains("linux", true) -> "linux"
                    else -> continue // Skip assets that don't match any platform
                }

                platformDownloadLinks[platform] = PlatformInfo(
                    signature = "",
                    url = asset.browserDownloadUrl
                )
            }

            call.respond(VersionData(
                response.tagName,
                response.body,
                response.publishedAt,
                platformDownloadLinks
            ))
        } catch (e: Exception) {
            call.respond(ResponseData("error", "Internal server error"))
        }
    }
}