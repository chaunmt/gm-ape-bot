plugins {
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}


application.mainClass = "bot.Bot"
group = "me.chaunmt.gm-ape-bot"
version = "1.0.0-alpha"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.13")
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    implementation("org.json:json:20231013")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true

    sourceCompatibility = "17"
}