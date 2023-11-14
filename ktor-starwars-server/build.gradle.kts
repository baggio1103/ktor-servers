import org.gradle.jvm.tasks.Jar

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val exposedVersion: String by project
val h2Version: String by project

plugins {
    kotlin("jvm") version "1.9.20"
    id("io.ktor.plugin") version "2.3.6"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
}

group = "javajedi.com"
version = "0.0.1"

application {
    mainClass.set("javajedi.com.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    // https://mvnrepository.com/artifact/org.kodein.di/kodein-di-jvm
    implementation("org.kodein.di:kodein-di-jvm:7.20.2")

    // Database
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    // HCP
    implementation("com.zaxxer:HikariCP:5.1.0")
    runtimeOnly("org.postgresql:postgresql:42.6.0")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}


tasks.named<Jar>("jar") {
    println("Project name: ${this.project.name}")
    archiveFileName.set("server-$version.jar")
}