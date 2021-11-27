val projectGroup: String by project
val projectVersion: String by project
val kotlinVersion: String by project
val jvmTarget: String by project

plugins {
    kotlin("multiplatform")
    `maven-publish`
}

group = "$projectGroup.kroote"
version = projectVersion


kotlin {

    jvm {
        withJava()
        compilations.all {
            kotlinOptions.jvmTarget = jvmTarget
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }

    js {
        browser()
        nodejs()
    }

    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting
        val jvmMain by getting
        val jvmTest by getting
        val nativeMain by getting
        val nativeTest by getting

    }
}
