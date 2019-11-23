package demo.nitin.tumblr_android_demo.features.dashboard

import demo.nitin.tumblr_android_demo.base.Dashboard
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.remote.Network
import demo.nitin.tumblr_android_demo.extensions.fullyTypedName
import demo.nitin.tumblr_android_demo.utils.Cache
import io.reactivex.Single

class DashboardRepoImpl constructor(private val network: Network) : DashboardRepo {
    override fun getPosts(offset: Int): Single<Posts> {
        val cachedData = Single.fromCallable {
            return@fromCallable if ((Cache[Dashboard::class.fullyTypedName()] as? Posts
                    ?: Dashboard(ArrayList())).uiPosts.size <= offset
            ) {
                Dashboard(ArrayList())
            } else {
                Cache[Dashboard::class.fullyTypedName()] as? Posts ?: Dashboard(ArrayList())
            }
        }

        val networkData = getNewPosts(offset)

        return Single.concat(cachedData, networkData).filter { it.uiPosts.isNotEmpty() }
            .first(Dashboard(ArrayList()))
    }

    override fun getNewPosts(offset: Int): Single<Posts> {
        return network.getPosts(offset).doOnSuccess {
            it?.let { posts ->
                if ((Cache[Dashboard::class.fullyTypedName()] as? Posts) == null) {
                    Cache.put(Dashboard::class.fullyTypedName(), Dashboard(ArrayList()))
                }
                (Cache[Dashboard::class.fullyTypedName()] as? Posts)?.uiPosts?.addAll(posts.uiPosts)
            }
        }.onErrorReturn {
            Dashboard(ArrayList())
        }
    }
}
