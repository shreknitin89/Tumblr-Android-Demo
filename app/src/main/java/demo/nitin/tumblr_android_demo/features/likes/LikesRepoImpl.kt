package demo.nitin.tumblr_android_demo.features.likes

import demo.nitin.tumblr_android_demo.base.Likes
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.remote.Network
import demo.nitin.tumblr_android_demo.extensions.fullyTypedName
import demo.nitin.tumblr_android_demo.utils.Cache
import io.reactivex.Single

class LikesRepoImpl constructor(private val network: Network) : LikesRepo {

    override fun getUserLikes(offset: Int): Single<Posts> {
        val cachedData = Single.fromCallable {
            return@fromCallable if ((Cache[Likes::class.fullyTypedName()] as? Posts
                    ?: Likes(ArrayList())).uiPosts.size <= offset
            ) {
                Likes(ArrayList())
            } else {
                Cache[Likes::class.fullyTypedName()] as? Posts ?: Likes(ArrayList())
            }
        }

        val networkData = getNewLikes(offset)

        return Single.concat(cachedData, networkData).filter { it.uiPosts.isNotEmpty() }
            .first(Likes(ArrayList()))
    }

    override fun getNewLikes(offset: Int): Single<Posts> {
        return network.getUserLikes(offset).doOnSuccess {
            it?.let { posts ->
                if ((Cache[Likes::class.fullyTypedName()] as? Posts) == null) {
                    Cache.put(Likes::class.fullyTypedName(), Likes(ArrayList()))
                }
                (Cache[Likes::class.fullyTypedName()] as? Posts)?.uiPosts?.addAll(posts.uiPosts)
            }
        }.onErrorReturn {
            Likes(ArrayList())
        }
    }
}
