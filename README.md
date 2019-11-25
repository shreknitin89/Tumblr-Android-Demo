# Tumblr-Android-Demo
An Android demo app using Tumblr API's and clean architecture

# Libraries:
1. [Jumblr](https://github.com/tumblr/jumblr) - For Tumblr API's
2. [Koin](https://github.com/InsertKoinIO/koin) - Dependency Injection
3. [Glide](https://github.com/bumptech/glide) - Image Loading
4. [RxAndroid](https://github.com/ReactiveX/RxAndroid) - Threading and communication
5. [JetPack Components](https://developer.android.com/jetpack/docs/getting-started) - LiveData, ViewModel, LifeCycle
6. [Kotlin Android Extensions](https://kotlinlang.org/docs/tutorials/android-plugin.html#view-binding) - View Binding, Parcelization
7. [Frag Nav](https://github.com/ncapdevi/FragNav) - Fragment management
8. [Mockito](https://github.com/mockito/mockito) - Mocking 
9. JUnit - Unit testing
10. Android core libraries like RecyclerView, CardView etc.
11. [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - Linter

# Notes:
In order to successfully run the app, one needs to replace the `Keys` in the file [`Dependencies.kt`](https://github.com/shreknitin89/Tumblr-Android-Demo/blob/master/buildSrc/src/main/kotlin/Dependencies.kt) as shown in the block below.
The file is located in the `buildSrc` package of the application

```//TODO: Replace with your keys
object Keys {
    const val consumerKey = "YOUR_CONSUMER_KEY"
    const val consumerSecret = "YOUR_CONSUMER_SECRET"
    const val token = "YOUR_TOKEN"
    const val tokenSecret = "YOUR_TOKEN_SECRET"
}
```

