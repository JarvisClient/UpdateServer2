# Jarvis Update and Feedback API

This is a Kotlin-based API for Jarvis Update and Feedback. It can be run in a Docker container.

## Prerequisites
- [Docker](https://www.docker.com/)

## Build the Application

1. Clone this repository to your local machine.

2. Navigate to the project directory:
   ```bash
   cd <your-project-directory>
   ```

3. Build the Kotlin project using Gradle:
   ```bash
   ./gradlew build
   ```

## Run with Docker

1. Make sure you have Docker installed and running on your system.

2. Fill in the enviromental variables in the docker-compose 

3. Build the Docker container using Docker Compose:
   ```bash
   docker-compose build
   ```

4. Start the Docker container:
   ```bash
   docker-compose up
   ```

The API should now be running in a Docker container and accessible at `http://localhost:3060`.

## API Endpoints

- You can access the API's endpoints at `http://localhost:3060`.

Documentation can be found at [/ENDPOINTS.md](/ENDPOINTS.md)

## Cleanup

To remove the Docker container and its associated images, run the following command:
```bash
docker-compose down
```