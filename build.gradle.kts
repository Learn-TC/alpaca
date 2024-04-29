import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
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
    implementation(compose.desktop.currentOs) {
        exclude(group = "org.jetbrains.compose.material")
    }

    implementation(libs.jewel)
    implementation(libs.jewel.window)
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
