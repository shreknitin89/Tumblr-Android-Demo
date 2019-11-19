package demo.nitin.tumblr_android_demo.di

import com.tumblr.jumblr.JumblrClient
import demo.nitin.tumblr_android_demo.BuildConfig
import demo.nitin.tumblr_android_demo.features.DashboardRepo
import demo.nitin.tumblr_android_demo.features.DashboardRepoImpl
import demo.nitin.tumblr_android_demo.features.remote.Network
import demo.nitin.tumblr_android_demo.features.remote.NetworkImpl
import demo.nitin.tumblr_android_demo.features.ui.DashboardViewModel
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
    single<Network> {
        NetworkImpl(get())
    }
    single<DashboardRepo> {
        DashboardRepoImpl(get())
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