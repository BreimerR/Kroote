val jvmTarget: String by project
val kspVersion: String by project
val ktorVersion: String by project
val projectGroup: String by project
val kotlinVersion: String by project
val projectVersion: String by project
val symbolProcessingVersion: String by project
val googleAutoServiceVersion: String by project

plugins {
    kotlin("multiplatform")
    `maven-publish`
}

group = projectGroup
version = projectVersion


/*dependencies {

    add {

        "com"{
            "squareup"("1.10.2") {
               //  +"kotlinpoet-ksp"
            }
            "google.auto.service"(googleAutoServiceVersion) {
                kaptImplement {
                    +"auto-service"
                }
                compileImplement {
                    +"auto-service"
                }
            }
            "google.devtools.ksp"{
                "symbol-processing-api"("$kotlinVersion-$symbolProcessingVersion")
            }
        }

        "javax.annotations.ext.processing"{
            "kapt"("1.0")
        }


        compileImplement {
            project {
                +"annotations"
            }
        }
    }

    implementation("io.ktor:ktor-server-core:$ktorVersion")

    testImplementation(kotlin("test"))

}*/


kotlin {

    jvm()

    sourceSets {

        val symbolProcessingApiDependency =
            "com.google.devtools.ksp:symbol-processing-api:$kotlinVersion-$symbolProcessingVersion"


        val jvmMain by getting {
            dependencies {
                implementation(symbolProcessingApiDependency)
                implementation(project(":annotations"))
            }

            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
        }
        val jvmTest by getting {
            dependencies {
                implementation(symbolProcessingApiDependency)
                implementation(project(":annotations"))
            }
            kotlin.srcDir("src/test/kotlin")
            resources.srcDir("src/test/resources")
        }
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}