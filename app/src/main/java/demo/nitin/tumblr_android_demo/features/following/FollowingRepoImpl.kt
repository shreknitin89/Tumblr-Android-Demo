package demo.nitin.tumblr_android_demo.features.following

import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.Dashboard
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.UiBlog
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

    override fun getBlogPosts(offset: Int, blog: UiBlog): Single<Posts> {
        val cachedData = Single.fromCallable {
            return@fromCallable if ((Cache[blog.name] as? Posts
                    ?: Dashboard(ArrayList())).uiPosts.size <= offset
            ) {
                Dashboard(ArrayList())
            } else {
                Cache[blog.name] as? Posts ?: Dashboard(ArrayList())
            }
        }

        val networkData = getNewBlogPosts(offset, blog)

        return Single.concat(cachedData, networkData).filter { it.uiPosts.isNotEmpty() }
            .first(Dashboard(ArrayList()))
    }

    override fun getNewBlogPosts(offset: Int, blog: UiBlog): Single<Posts> {
        return network.getBlogPosts(offset, blog).doOnSuccess {
            it?.let { posts ->
                if ((Cache[blog.name] as? Posts) == null) {
                    Cache.put(blog.name, Dashboard(ArrayList()))
                }
                (Cache[blog.name] as? Posts)?.uiPosts?.addAll(posts.uiPosts)
            }
        }.onErrorReturn {
            Dashboard(ArrayList())
        }
    }

}