package demo.nitin.tumblr_android_demo.features.likes

import demo.nitin.tumblr_android_demo.base.Likes
import demo.nitin.tumblr_android_demo.base.remote.Network
import demo.nitin.tumblr_android_demo.extensions.fullyTypedName
import demo.nitin.tumblr_android_demo.utils.Cache
import io.reactivex.Single

class LikesRepoImpl constructor(private val network: Network) : LikesRepo {
    override fun getNewLikes(): Single<Likes> {
        return network.getUserLikes()
    }

    override fun getUserLikes(): Single<Likes> {

        val cachedData = Single.fromCallable {
            return@fromCallable Cache[Likes::class.fullyTypedName()] as? Likes ?: Likes(ArrayList())
        }
        val networkData = network.getUserLikes().doOnSuccess {
            it?.let { posts ->
                (Cache[Likes::class.fullyTypedName()] as? Likes)?.uiPosts?.addAll(posts.uiPosts)
            }
        }

        return Single.concat(cachedData, networkData).filter { it.uiPosts.isNotEmpty() }
            .first(Likes(ArrayList()))
    }
}