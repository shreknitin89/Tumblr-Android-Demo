package demo.nitin.tumblr_android_demo.features

import demo.nitin.tumblr_android_demo.extensions.fullyTypedName
import demo.nitin.tumblr_android_demo.features.base.Posts
import demo.nitin.tumblr_android_demo.features.remote.Network
import demo.nitin.tumblr_android_demo.utils.Cache
import io.reactivex.Single

class DashboardRepoImpl constructor(private val network: Network) : DashboardRepo {
    override fun getPosts(offset: Int): Single<Posts> {
        val cachedData = Single.fromCallable {
            return@fromCallable Cache[Posts::class.fullyTypedName()] as? Posts ?: Posts(ArrayList())
        }
        val networkData = network.getPosts(offset).doOnSuccess {
            it?.let { posts ->
                (Cache[Posts::class.fullyTypedName()] as? Posts)?.uiPosts?.addAll(posts.uiPosts)
            }
        }

        return Single.concat(cachedData, networkData).filter { it.uiPosts.isNotEmpty() }.first(Posts(ArrayList()))
    }
}