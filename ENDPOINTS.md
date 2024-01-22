# API Endpoints Documentation

## Endpoints
### 1. GET /latest

#### Request
- **Method:** GET
- **Endpoint:** `/latest`
- **Headers:** N/A (unless you have specific headers like authorization tokens)
- **Query Parameters:** N/A (unless there are optional query parameters to modify the request)

#### Example Response
```json
{
  "notes": "**Full Changelog**: [View the full changelog](https://github.com/JarvisClient/Client/compare/0.9.76...0.9.77)",
  "platforms": {
    "darwin-aarch64": {
      "signature": "",
      "url": "https://github.com/JarvisClient/Client/releases/download/0.9.77/Jarvis_0.9.77_macOS_Release.tar.gz"
    },
    "darwin-x86_64": {
      "signature": "",
      "url": "https://github.com/JarvisClient/Client/releases/download/0.9.77/Jarvis_0.9.77_macOS_Release.tar.gz"
    },
    "linux-x86_64": {
      "signature": "",
      "url": "https://github.com/JarvisClient/Client/releases/download/0.9.77/Jarvis_0.9.77_Linux_Release.tar.gz"
    },
    "windows-x86_64": {
      "signature": "",
      "url": "https://github.com/JarvisClient/Client/releases/download/0.9.77/Jarvis_0.9.77_Windows_Release.zip"
    }
  },
  "pub_date": "2024-01-18T17:19:16Z",
  "version": "0.9.77"
}
```

### 2. POST /api/feedback

#### Request
- **Method:** POST
- **Endpoint:** `/api/feedback`
- **Headers:** `Content-Type: application/json`
- **Body:** JSON object containing feedback details
  - `feedbackLike` (String)
  - `feedbackDislike` (String)
  - `feedbackEmoji` (String): Emoji used for feedback.

#### Example Request Body
```json
{
  "feedbackLike": "Lorem Ipsum",
  "feedbackDislike": "Lorem Ipsum",
  "feedbackEmoji": "👍"
}
```

#### Response
- **Content-Type:** `application/json`
- **Body:**
  - `status` (String): Status of the request (e.g., "success", "failure").
  - `message` (String): Additional information or confirmation message.

#### Example Response
```json
{
  "status": "success",
  "message": "Feedback received successfully."
}
```
