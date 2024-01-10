plugins {
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}


application.mainClass = "me.chaunmt.gmapebot.Bot"
group = "me.chaunmt.gmapebot"
version = "1.0.0-alpha.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.19")
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true

    sourceCompatibility = "17"
}