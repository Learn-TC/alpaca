import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version(libs.versions.kotlin)
    alias(libs.plugins.compose)
    alias(libs.plugins.serialization)
}

group = "com.github.learn-tc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://packages.jetbrains.team/maven/p/kpm/public/")
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Jetpack Compose
    implementation(libs.jewel)
    implementation(libs.jewel.window)
    implementation(compose.desktop.currentOs) {
        exclude(group = "org.jetbrains.compose.material")
    }

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.swing)

    // HTTP Client
    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.serialization)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "alpaca"
            packageVersion = "1.0.0"
        }
    }
}
