import com.android.build.gradle.BaseExtension
import org.gradle.internal.impldep.org.apache.maven.model.Extension

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

// configure<BaseExtension>
android {
    compileSdkVersion(Android.compileSdkVersion)
    defaultConfig {
        applicationId = Android.applicationId
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = Android.versionCode
        versionName = Android.versionName
        multiDexEnabled = true
        testInstrumentationRunner = Android.testInstrumentationRunner
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    implementation("androidx.appcompat:appcompat:${Libs.appCompat}")
    implementation("androidx.constraintlayout:constraintlayout:${Libs.constraintLayout}")

    implementation("androidx.core:core-ktx:${Libs.androidCore}")

    // Unit Tests
    testImplementation("junit:junit:${Libs.junit}")
    androidTestImplementation("androidx.test.ext:junit:${Libs.junitExt}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Libs.espresso}")
}
