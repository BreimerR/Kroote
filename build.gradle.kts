buildscript {

    repositories {
        mavenCentral()
        mavenLocal()

    }

    val kotlinVersion: String by project

    dependencies {
        classpath(kotlin("gradle-plugin", kotlinVersion))
    }
}


allprojects {
    repositories {
        mavenCentral()
        mavenLocal()

    }
}

