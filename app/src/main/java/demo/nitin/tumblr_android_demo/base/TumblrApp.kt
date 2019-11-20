package demo.nitin.tumblr_android_demo.base

import android.app.Application
import demo.nitin.tumblr_android_demo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.bumptech.glide.module.AppGlideModule
import androidx.core.content.ContextCompat.getSystemService
import com.bumptech.glide.annotation.GlideModule

/**
 * Application class for defining the starting point and also to define the DI libraries
 * @author - Nitin Dasari
 * @since - 10/17/2019
 */
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