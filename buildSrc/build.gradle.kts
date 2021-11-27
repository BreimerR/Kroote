plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    mavenLocal()
}
val kotlinVersion = "1.5.31"

dependencies {
    implementation(kotlin("gradle-plugin", kotlinVersion))
    implementation("libetal.gradle.plugins:libBuildSrc:1.5")
}