const val kotlinVersion = "1.3.50"

object BuildPlugins {
    const val ktLintPlugin = "9.1.1" // https://github.com/JLLeitschuh/ktlint-gradle/releases
    const val androidGradle = "3.5.2" // https://developer.android.com/studio/releases/gradle-plugin
}

object Android {
    const val minSdkVersion = 23
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29
    const val applicationId = "demo.nitin.timblr_android_demo"
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Keys {
    const val consumerKey = "lsnTmAkmV50oeGg7oVaOGzg48dmBGr5yS8eViGXxmYTtKdMMyv"
    const val consumerSecret = "Myw99AKRzK3HdrArDuAGOIKLLHR9I9ucOhTkh7YqLjfmVjDMrr"
    const val token = "TyKAE4fM1P4vkGTQHa6dD2yAoq77o4chmG4evr3nC7f7vIdR78"
    const val tokenSecret = "SwXwBkwhuJsoOMI8gLVQiOd7yPbSfF1AMYdrPE5ZOgVJyAEImn"
}

object Libs {
    const val appCompat = "1.1.0" // https://mvnrepository.com/artifact/androidx.appcompat/appcompat
    const val androidMaterial = "1.1.0-beta01"
    // https://mvnrepository.com/artifact/com.google.android.material/material
    const val recyclerView = "1.1.0-beta05"
    // https://mvnrepository.com/artifact/androidx.recyclerview/recyclerview
    const val cardView = "1.0.0" // https://mvnrepository.com/artifact/androidx.cardview/cardview
    const val legacySupport = "1.0.0" // https://mvnrepository.com/artifact/androidx.legacy/legacy-support-v4
    const val androidLifeCycle = "2.1.0"
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-extensions
    const val lifeCycleSavedState = "1.0.0-beta01"
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-savedstate
    const val activitySavedState =
        "1.0.0" // https://mvnrepository.com/artifact/androidx.savedstate/savedstate
    const val vectorDrawable =
        "1.1.0" // https://mvnrepository.com/artifact/androidx.vectordrawable/vectordrawable
    const val legacySupportCore = "1.0.0"
    const val constraintLayout = "2.0.0-beta2"
    // https://mvnrepository.com/artifact/androidx.constraintlayout/constraintlayout
    const val jumblr = "0.0.13" // https://github.com/tumblr/jumblr/releases
    const val multidex = "2.0.1" // https://mvnrepository.com/artifact/androidx.multidex/multidex
    const val okHttp = "4.2.2" // https://github.com/square/okhttp/releases
    const val retrofit = "2.6.1" // https://github.com/square/retrofit/releases
    const val glide = "4.10.0" // https://github.com/bumptech/glide/releases
    const val jodaTime = "2.10.4" // https://github.com/JodaOrg/joda-time/releases
    const val jodaConvert = "2.2.1" // http://www.joda.org/joda-convert/changes-report.html
    const val koin = "2.0.1" // https://github.com/InsertKoinIO/koin/releases
    const val timberLog = "4.7.1" // https://github.com/JakeWharton/timber/releases
    const val fragNav = "3.3.0" // https://github.com/ncapdevi/FragNav/releases
    const val rxAndroid = "2.1.1" // https://github.com/ReactiveX/RxAndroid/releases
    const val instantApp =
        "1.1.0" // https://mvnrepository.com/artifact/com.google.android.instantapps/instantapps
    const val androidCore = "1.1.0" // https://mvnrepository.com/artifact/androidx.core/core-ktx
    const val androidxParcelable = "1.1.0"
    // https://mvnrepository.com/artifact/androidx.versionedparcelable/versionedparcelable
    const val coordinatorLayout = "1.1.0-beta01"
    // https://mvnrepository.com/artifact/androidx.coordinatorlayout/coordinatorlayout
    const val androidxAnnotation =
        "1.1.0" // https://mvnrepository.com/artifact/androidx.annotation/annotation
    const val androidxCollection =
        "1.1.0" // https://mvnrepository.com/artifact/androidx.collection/collection
    const val fragment =
        "1.2.0-beta02"  // https://mvnrepository.com/artifact/androidx.fragment/fragment
    const val jetbrainAnnotation =
        "17.0.0" // https://mvnrepository.com/artifact/org.jetbrains/annotations
    const val gson = "2.8.5"

    // region Instrumented Testing
    const val junit = "4.12"
    const val junitExt = "1.1.1"
    const val espresso =
        "3.2.0" // https://mvnrepository.com/artifact/androidx.test.espresso/espresso-core
    const val mockito = "3.1.0" // https://mvnrepository.com/artifact/org.mockito/mockito-core
    // endregion
}
