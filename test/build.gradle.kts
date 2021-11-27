import libetal.gradle.add

val jvmTarget: String by project


plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp") version "1.5.31-1.0.1"

}

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
        val commonMain by getting {
            dependencies {
                implementation(project(":annotations"))
            }
        }
        val commonTest by getting

        val jsMain by getting
        val jsTest by getting
        val jvmMain by getting{
            dependencies{
                add(project){
                    "io.ktor"("1.6.4") {
                        "ktor"('-', version = version) {
                            +"auth"
                            +"gson"
                            +"serialization"
                            +"locations"
                            +"utils"
                            "server" {
                                +"core"
                                +"netty"
                            }
                            "client" {
                                +"core"
                                +"java"
                            }
                        }
                    }
                }
            }
        }
        val jvmTest by getting
        val nativeMain by getting
        val nativeTest by getting

    }
}

dependencies {
    //  add("kspMetadata", project(":kroote"))
    add("kspJvm", project(":kroote"))
    add("kspJvmTest", project(":kroote"))

/*    add("kspMetadata", project(":kroote"))
    add("kspJs", project(":kroote"))
    add("kspJsTest", project(":kroote"))*/
    /*   add("kspAndroidNativeArm64", project(":kroote"))
       add("kspAndroidNativeArm64Test", project(":kroote"))
       add("kspLinuxX64", project(":kroote"))
       add("kspLinuxX64Test", project(":kroote"))
       add("kspMingwX64", project(":kroote"))
       add("kspMingwX64Test", project(":kroote"))
       */
}