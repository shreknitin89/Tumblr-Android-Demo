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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:${BuildPlugins.androidGradle}")
    }
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
