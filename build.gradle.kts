
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.7"
    kotlin("plugin.serialization") version "1.4.21"
}

group = "com.jnsaph.jarvis-update-server"
version = "0.0.1"

application {
    mainClass.set("com.jnsaph.jarvis_update_server.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

tasks.register<Jar>("buildJar") {
    manifest {
        attributes(mapOf("Main-Class" to "com.jnsaph.jarvis_update_server.ApplicationKt"))
    }
    archiveBaseName.set("BaseName")
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else project.zipTree(it) })
    with(tasks.jar.get())

    // Handling duplicate entries
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


dependencies {
    // Ktor Server Core Dependencies
    implementation("io.ktor:ktor-server-cors-jvm")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")

    // Logging for Ktor Server
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Testing Dependencies for Ktor Server
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // Ktor Server Features
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")

    // Ktor Client Core Dependencies
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")

    // JSON Handling for Ktor Client
    implementation("io.ktor:ktor-client-json:1.6.4")
    implementation("io.ktor:ktor-client-serialization:1.6.4")

    // Kotlin coroutine dependency
    implementation("org.postgresql:postgresql:42.7.1")
}