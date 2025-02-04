import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm") version "2.1.20-Beta2"

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    maven("https://jitpack.io")
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("com.github.ptnplanet:Java-Naive-Bayes-Classifier:1.0.7")
    implementation("com.github.ajalt.clikt:clikt:4.2.2")

    testImplementation("org.assertj:assertj-core:3.25.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation("io.mockk:mockk:1.13.6")


    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

application {
    // Define the main class for the application.
    mainClass.set("com.github.hannotify.classyfire.ClassyFireKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "21"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
