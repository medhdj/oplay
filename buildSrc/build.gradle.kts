val androidGradleDependency = "com.android.tools.build:gradle:7.0.2"
val kotlinGradleDependency = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31"
val detektGradleDependency = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.18.1"

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(androidGradleDependency)
    implementation(kotlinGradleDependency)
    implementation (detektGradleDependency)
}