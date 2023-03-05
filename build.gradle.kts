import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    //johnrengelman shadow
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.kingOf0"
version = "3.0.2"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly ("org.spigotmc:spigot:1.18.1-R0.1-SNAPSHOT")

    compileOnly("org.jetbrains.kotlin:kotlin-stdlib")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
    //com github cryptomorin xseries
    compileOnly("com.github.cryptomorin:XSeries:8.7.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("KCommandNegater.jar")
    relocate ("com.google.gson", "com.kingOf0.kspawners.shade.gson")

    relocate ("de.tr7zw", "com.kingOf0.tr7zw")
    relocate("kotlin", "com.kingOf0.kotlin")
    relocate("kotlinx", "com.kingOf0.kotlinx")
    relocate("com.cryptomorin", "com.kingOf0.xseries")
}