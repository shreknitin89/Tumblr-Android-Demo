import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("org.jlleitschuh.gradle.ktlint")
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

        buildConfigField("String", "CONSUMER_KEY", "\"${Keys.consumerKey}\"")
        buildConfigField("String", "CONSUMER_SECRET", "\"${Keys.consumerSecret}\"")
        buildConfigField("String", "TOKEN", "\"${Keys.token}\"")
        buildConfigField("String", "TOKEN_SECRET", "\"${Keys.tokenSecret}\"")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    androidExtensions.isExperimental = true
    sourceSets {
        val kotlinAdditionalSourceSets = project.file("src/main/kotlin")
        findByName("main")?.java?.srcDirs(kotlinAdditionalSourceSets)
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    // UI core components
    implementation("androidx.appcompat:appcompat:${Libs.appCompat}")
    implementation("androidx.constraintlayout:constraintlayout:${Libs.constraintLayout}")
    implementation("com.google.android.material:material:${Libs.androidMaterial}")
    implementation("androidx.core:core-ktx:${Libs.androidCore}")
    implementation("androidx.legacy:legacy-support-v4:${Libs.legacySupport}")
    implementation("androidx.lifecycle:lifecycle-extensions:${Libs.androidLifeCycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Libs.androidLifeCycle}")

    // Glide for Image Loading
    kapt("com.github.bumptech.glide:compiler:${Libs.glide}")
    implementation("com.github.bumptech.glide:glide:${Libs.glide}")
    implementation("com.github.bumptech.glide:okhttp3-integration:${Libs.glide}")

    // Tumblr
    implementation("com.tumblr:jumblr:${Libs.jumblr}")

    // Fragment Navigation
    implementation("com.ncapdevi:frag-nav:${Libs.fragNav}")

    // RxAndroid
    implementation("io.reactivex.rxjava2:rxandroid:${Libs.rxAndroid}")

    // Koin for Android
    implementation("org.koin:koin-android:${Libs.koin}")
    implementation("org.koin:koin-android-scope:${Libs.koin}")
    implementation("org.koin:koin-android-viewmodel:${Libs.koin}")
    implementation("org.koin:koin-android-ext:${Libs.koin}")
    androidTestImplementation("org.koin:koin-test:${Libs.koin}") {
        exclude("org.mockito")
    }

    // Unit Tests
    testImplementation("junit:junit:${Libs.junit}")
    androidTestImplementation("org.mockito:mockito-android:${Libs.mockito}")
    testImplementation("org.mockito:mockito-core:${Libs.mockito}")
    androidTestImplementation("androidx.test.ext:junit:${Libs.junitExt}")
    androidTestImplementation("android.arch.core:core-testing:${Libs.junitExt}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Libs.espresso}")
}

ktlint {
    android.set(true)
    verbose.set(true)
    outputToConsole.set(true)
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
    }
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}
