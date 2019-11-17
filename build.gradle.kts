// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    extra["kotlin_version"] = kotlinVersion

    repositories {
        google()
        jcenter()
        flatDir {
            dirs("libs")
        }
    }

    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:${BuildPlugins.ktLintPlugin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:${BuildPlugins.androidGradle}")
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint").version(BuildPlugins.ktLintPlugin)
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
