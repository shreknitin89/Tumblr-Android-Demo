package demo.nitin.tumblr_android_demo.di

import com.tumblr.jumblr.JumblrClient
import demo.nitin.tumblr_android_demo.BuildConfig
import demo.nitin.tumblr_android_demo.base.remote.Network
import demo.nitin.tumblr_android_demo.base.remote.NetworkImpl
import demo.nitin.tumblr_android_demo.features.dashboard.DashboardRepo
import demo.nitin.tumblr_android_demo.features.dashboard.DashboardRepoImpl
import demo.nitin.tumblr_android_demo.features.dashboard.DashboardViewModel
import demo.nitin.tumblr_android_demo.features.following.FollowingRepo
import demo.nitin.tumblr_android_demo.features.following.FollowingRepoImpl
import demo.nitin.tumblr_android_demo.features.following.FollowingViewModel
import demo.nitin.tumblr_android_demo.features.likes.LikesRepo
import demo.nitin.tumblr_android_demo.features.likes.LikesRepoImpl
import demo.nitin.tumblr_android_demo.features.likes.LikesViewModel
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
    single<LikesRepo> {
        LikesRepoImpl(get())
    }
    single<FollowingRepo> {
        FollowingRepoImpl(get())
    }
    viewModel {
        DashboardViewModel(get())
    }
    viewModel {
        LikesViewModel(get())
    }
    viewModel {
        FollowingViewModel(get())
    }
}

private fun getClient(): JumblrClient {
    val client = JumblrClient(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET)
    client.setToken(BuildConfig.TOKEN, BuildConfig.TOKEN_SECRET)
    return client
}
