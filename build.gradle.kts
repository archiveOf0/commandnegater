import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    //johnrengelman shadow
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.kingOf0"
version = "1.0.4"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly ("org.spigotmc:spigot:1.18.1-R0.1-SNAPSHOT")

    //kotlin 8
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72")
    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}