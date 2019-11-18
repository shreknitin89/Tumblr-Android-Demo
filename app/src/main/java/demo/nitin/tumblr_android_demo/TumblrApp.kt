package demo.nitin.tumblr_android_demo

import android.app.Application
import demo.nitin.tumblr_android_demo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TumblrApp: Application() {

    override fun onCreate() {
        super.onCreate()

        //start koin
        startKoin {
            androidLogger()
            androidContext(this@TumblrApp)
            modules(listOf(appModule))
        }
    }
}