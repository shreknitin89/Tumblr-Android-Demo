package demo.nitin.tumblr_android_demo.features.following

import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.remote.Network
import demo.nitin.tumblr_android_demo.extensions.fullyTypedName
import demo.nitin.tumblr_android_demo.utils.Cache
import io.reactivex.Single

class FollowingRepoImpl constructor(private val network: Network) : FollowingRepo {
    override fun getBlogs(offset: Int): Single<Blogs> {
        val cachedData = Single.fromCallable {
            return@fromCallable Cache[Blogs::class.fullyTypedName()] as? Blogs ?: Blogs(ArrayList())
        }
        val networkData = getNewBlogs(offset)

        return Single.concat(cachedData, networkData).filter { it.uiBlogs.isNotEmpty() }
            .first(Blogs(ArrayList()))
    }

    override fun getNewBlogs(offset: Int): Single<Blogs> {
        return network.getBlogs(offset).doOnSuccess {
            it?.let { blogs ->
                if ((Cache[Blogs::class.fullyTypedName()] as? Blogs) == null) {
                    Cache.put(Blogs::class.fullyTypedName(), Blogs(ArrayList()))
                }
                (Cache[Blogs::class.fullyTypedName()] as? Blogs)?.uiBlogs?.addAll(blogs.uiBlogs)
            }
        }
            .onErrorReturn {
                Blogs(ArrayList())
            }
    }
}