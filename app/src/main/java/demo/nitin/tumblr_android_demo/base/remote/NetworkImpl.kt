package demo.nitin.tumblr_android_demo.base.remote

import com.tumblr.jumblr.JumblrClient
import com.tumblr.jumblr.types.PhotoPost
import com.tumblr.jumblr.types.TextPost
import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.Dashboard
import demo.nitin.tumblr_android_demo.base.Likes
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.UiBlog
import demo.nitin.tumblr_android_demo.base.UiPost
import io.reactivex.Single

class NetworkImpl constructor(private val client: JumblrClient) : Network {
    override fun getPosts(offset: Int): Single<Posts> {
        return Single.create { emitter ->
            val map = HashMap<String, Int>()
            map["offset"] = offset
            try {
                client.userDashboard(map)?.let { dashboard ->
                    val posts = dashboard.map {
                        val blogName = it?.blogName ?: ""
                        val title = it?.slug ?: ""
                        val description = (it as? TextPost)?.body ?: ""
                        val photo =
                            (it as? PhotoPost)?.photos?.firstOrNull()?.originalSize?.url ?: ""
                        val tags = it?.tags?.joinToString() ?: ""
                        return@map UiPost(blogName, title, description, photo, tags)
                    } as? ArrayList ?: ArrayList()
                    emitter.onSuccess(Dashboard(posts))
                } ?: run {
                    emitter.tryOnError(Throwable("Dashboards posts empty"))
                }
            } catch (exception: Exception) {
                emitter.tryOnError(exception)
            }
        }
    }

    override fun getBlogs(offset: Int): Single<Blogs> {
        return Single.create { emitter ->
            try {
                val map = HashMap<String, Int>()
                map["offset"] = offset
                client.userFollowing(map)?.let { blogs ->
                    val uiBlogs = blogs.map {
                        UiBlog(
                            it.name,
                            it.title,
                            it.description,
                            it.postCount,
                            it.likeCount,
                            it.followersCount
                        )
                    } as? ArrayList ?: ArrayList()
                    emitter.onSuccess(Blogs(uiBlogs as? ArrayList ?: ArrayList()))
                } ?: run {
                    emitter.tryOnError(Throwable("Followers empty"))
                }
            } catch (exception: Exception) {
                emitter.tryOnError(exception)
            }
        }
    }

    override fun getUserLikes(offset: Int): Single<Posts> {
        return Single.create { emitter ->
            try {
                val map = HashMap<String, Int>()
                map["offset"] = offset
                client.userLikes(map)?.let { likes ->
                    val posts = likes.map {
                        val blogName = it?.blogName ?: ""
                        val title = it?.slug ?: ""
                        val description = (it as? TextPost)?.body ?: ""
                        val photo =
                            (it as? PhotoPost)?.photos?.firstOrNull()?.originalSize?.url ?: ""
                        val tags = it?.tags?.joinToString() ?: ""
                        return@map UiPost(blogName, title, description, photo, tags)
                    } as? ArrayList ?: ArrayList()
                    emitter.onSuccess(Likes(posts))
                } ?: run {
                    emitter.tryOnError(Throwable("Liked posts empty"))
                }
            } catch (exception: Exception) {
                emitter.tryOnError(exception)
            }
        }
    }

    override fun getBlogPosts(offset: Int, blog: UiBlog): Single<Posts> {
        return Single.create { emitter ->
            val map = HashMap<String, Int>()
            map["offset"] = offset
            try {
                val blogInfo = client.blogInfo(blog.name)
                blogInfo.posts(map)?.let { dashboard ->
                    val posts = dashboard.map {
                        val blogName = it?.blogName ?: ""
                        val title = it?.slug ?: ""
                        val description = (it as? TextPost)?.body ?: ""
                        val photo =
                            (it as? PhotoPost)?.photos?.firstOrNull()?.originalSize?.url ?: ""
                        val tags = it?.tags?.joinToString() ?: ""
                        return@map UiPost(blogName, title, description, photo, tags)
                    } as? ArrayList ?: ArrayList()
                    emitter.onSuccess(Dashboard(posts))
                } ?: run {
                    emitter.tryOnError(Throwable("Dashboards posts empty"))
                }
            } catch (exception: Exception) {
                emitter.tryOnError(exception)
            }
        }
    }
}
