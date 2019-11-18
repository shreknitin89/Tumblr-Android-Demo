package demo.nitin.tumblr_android_demo.di

import com.tumblr.jumblr.JumblrClient
import demo.nitin.tumblr_android_demo.BuildConfig
import demo.nitin.tumblr_android_demo.ui.DashboardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Module used to inject dependencies through out the application
 * @author - Nitin Dasari
 * @since - 11/17/2019
 */
val appModule = module {
    single {
        getClient()
    }
    viewModel {
        DashboardViewModel(get())
    }
}

private fun getClient(): JumblrClient {
    val client = JumblrClient(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET)
    client.setToken(BuildConfig.TOKEN, BuildConfig.TOKEN_SECRET)
    return client
}