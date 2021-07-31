import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
}

group = "me.kingOf0"
version = "1.0.2"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly ("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}