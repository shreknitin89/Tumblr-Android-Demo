package demo.nitin.tumblr_android_demo.features.following

import com.tumblr.jumblr.types.Blog
import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.remote.Network
import demo.nitin.tumblr_android_demo.extensions.fullyTypedName
import demo.nitin.tumblr_android_demo.utils.Cache
import io.reactivex.Single

class FollowingRepoImpl constructor(private val network: Network) : FollowingRepo {
    override fun getBlogs(): Single<Blogs> {
        val cachedData = Single.fromCallable {
            return@fromCallable Cache[Blog::class.fullyTypedName()] as? Blogs ?: Blogs(ArrayList())
        }
        val networkData = network.getBlogs().doOnSuccess {
            it?.let { blogs ->
                (Cache[Blogs::class.fullyTypedName()] as? Blogs)?.uiBlogs?.addAll(blogs.uiBlogs)
            }
        }

        return Single.concat(cachedData, networkData).filter { it.uiBlogs.isNotEmpty() }
            .first(Blogs(ArrayList()))
    }

    override fun getNewBlogs(): Single<Blogs> {
        return network.getBlogs()
    }
}