buildscript {
    repositories { mavenCentral() }

    dependencies {
        val kotlinVersion = "1.7.10"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.2.2").apply(false)
    id("com.android.library").version("7.2.2").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
