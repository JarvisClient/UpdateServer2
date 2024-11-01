package com.jnsaph.jarvis_update_server.fetch

import com.jnsaph.jarvis_update_server.models.github.releases.GitHubReleaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GitHubAPI(
    private val username: String,
    private val repository: String,
    private val accessToken: String
) {
    private val baseUrl = "https://api.github.com/repos/$username/$repository"
    private val httpClient = HttpClient(CIO)

    // Configure Json to ignore unknown keys
    private val json = Json { ignoreUnknownKeys = true }

    init {
        require(username.isNotEmpty() && repository.isNotEmpty() && accessToken.isNotEmpty()) {
            if (username.isEmpty()) println("GITHUB_API_USERNAME is not set.")
            if (repository.isEmpty()) println("GITHUB_API_REPOSITORY is not set.")
            if (accessToken.isEmpty()) println("GITHUB_API_TOKEN is not set.")
        }
    }

    suspend fun getLatestRelease(): GitHubReleaseResponse {
        val url = "$baseUrl/releases/latest"

        try {
            // Make request with header and auth
            val response: HttpResponse = httpClient.get(url) {
                header("Authorization", "token $accessToken")
            }

            // Check if response is OK
            if (response.status == HttpStatusCode.OK) {
                // Deserialize response body to GitHubReleaseResponse
                val responseBody = response.bodyAsText()
                return json.decodeFromString<GitHubReleaseResponse>(responseBody)
            } else {
                throw Exception("Invalid response code: ${response.status}")
            }

        } catch (e: Exception) {
            throw e
        }
    }
}
