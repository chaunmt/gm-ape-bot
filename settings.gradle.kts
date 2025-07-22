pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        kotlin("jvm") version "1.9.22"
        id("com.github.johnrengelman.shadow") version "8.1.1"
    }
}

rootProject.name = "gm-ape-bot"
