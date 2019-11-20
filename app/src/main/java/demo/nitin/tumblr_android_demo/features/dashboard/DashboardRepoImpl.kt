package demo.nitin.tumblr_android_demo.features.dashboard

import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.remote.Network
import demo.nitin.tumblr_android_demo.extensions.fullyTypedName
import demo.nitin.tumblr_android_demo.utils.Cache
import io.reactivex.Single

class DashboardRepoImpl constructor(private val network: Network) : DashboardRepo {
    override fun getPosts(): Single<Posts> {
        val cachedData = Single.fromCallable {
            return@fromCallable Cache[Posts::class.fullyTypedName()] as? Posts ?: Posts(ArrayList())
        }
        val networkData = network.getPosts().doOnSuccess {
            it?.let { posts ->
                if ((Cache[Posts::class.fullyTypedName()] as? Posts) == null) {
                    Cache.put(Posts::class.fullyTypedName(), Posts(ArrayList()))
                }
                (Cache[Posts::class.fullyTypedName()] as? Posts)?.uiPosts?.addAll(posts.uiPosts)
            }
        }

        return Single.concat(cachedData, networkData).filter { it.uiPosts.isNotEmpty() }
            .first(Posts(ArrayList()))
    }

    override fun getNewPosts(): Single<Posts> {
        return network.getPosts()
    }
}