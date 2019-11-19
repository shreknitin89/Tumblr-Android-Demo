package demo.nitin.tumblr_android_demo.features

import demo.nitin.tumblr_android_demo.extensions.fullyTypedName
import demo.nitin.tumblr_android_demo.features.models.Posts
import demo.nitin.tumblr_android_demo.features.remote.Network
import demo.nitin.tumblr_android_demo.utils.Cache
import io.reactivex.Flowable
import io.reactivex.Single

class DashboardRepoImpl constructor(private val network: Network) : DashboardRepo {
    override fun getPosts(offset: Int): Flowable<Any> {
        val cachedData = Single.fromCallable {
            return@fromCallable Cache[Posts::class.fullyTypedName()]
        }
        val networkData = network.getPosts(offset).doOnSuccess {
            it?.let { posts ->
                Cache.put(Posts::class.fullyTypedName(), posts)
            }
        }

        return Single.concat(cachedData, networkData)
    }
}