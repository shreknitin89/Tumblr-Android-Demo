package demo.nitin.tumblr_android_demo.base.remote

import com.tumblr.jumblr.JumblrClient
import com.tumblr.jumblr.types.PhotoPost
import com.tumblr.jumblr.types.TextPost
import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.Likes
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.UiBlog
import demo.nitin.tumblr_android_demo.base.UiPost
import io.reactivex.Single

class NetworkImpl constructor(private val client: JumblrClient) : Network {
    override fun getPosts(): Single<Posts> {
        return Single.create { emitter ->
            client.userDashboard()?.let { dashboard ->
                val posts = dashboard.map {
                    val blogName = it?.blogName ?: ""
                    val title = it?.slug ?: ""
                    val description = (it as? TextPost)?.body ?: ""
                    val photo = (it as? PhotoPost)?.photos?.firstOrNull()?.originalSize?.url ?: ""
                    val tags = it?.tags?.joinToString() ?: ""
                    return@map UiPost(blogName, title, description, photo, tags)
                } as? ArrayList ?: ArrayList()
                emitter.onSuccess(Posts(posts))
            } ?: run {
                emitter.tryOnError(Throwable("Dashboards posts empty"))
            }
        }
    }

    override fun getBlogs(): Single<Blogs> {

        return Single.create { emitter ->
            client.userFollowing()?.let { blogs ->
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
        }
    }

    override fun getUserLikes(): Single<Likes> {
        return Single.create { emitter ->
            client.userLikes()?.let { likes ->
                val posts = likes.map {
                    val blogName = it?.blogName ?: ""
                    val title = it?.slug ?: ""
                    val description = (it as? TextPost)?.body ?: ""
                    val photo = (it as? PhotoPost)?.photos?.firstOrNull()?.originalSize?.url ?: ""
                    val tags = it?.tags?.joinToString() ?: ""
                    return@map UiPost(blogName, title, description, photo, tags)
                } as? ArrayList ?: ArrayList()
                emitter.onSuccess(Likes(posts))
            } ?: run {
                emitter.tryOnError(Throwable("Liked posts empty"))
            }
        }
    }
}