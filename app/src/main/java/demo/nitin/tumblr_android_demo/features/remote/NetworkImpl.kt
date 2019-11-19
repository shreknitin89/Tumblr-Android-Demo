package demo.nitin.tumblr_android_demo.features.remote

import com.tumblr.jumblr.JumblrClient
import com.tumblr.jumblr.types.PhotoPost
import com.tumblr.jumblr.types.TextPost
import demo.nitin.tumblr_android_demo.features.models.Posts
import demo.nitin.tumblr_android_demo.features.models.UiPost
import io.reactivex.Single

class NetworkImpl constructor(private val client: JumblrClient) : Network {
    override fun getPosts(offset: Int): Single<Posts> {
        return Single.create { emitter ->
            client.userDashboard()?.let { dashboard ->
                val posts = dashboard.map {
                    val blogName = it?.blogName ?: ""
                    val title = it?.slug ?: ""
                    val description = (it as? TextPost)?.body ?: ""
                    val photo = (it as? PhotoPost)?.photos?.firstOrNull()?.originalSize?.url ?: ""
                    val tags = it?.tags?.joinToString() ?: ""
                    return@map UiPost(blogName, title, description, photo, tags)
                } as ArrayList
                emitter.onSuccess(Posts(posts))
            } ?: run {
                emitter.tryOnError(Throwable("Dashboards posts empty"))
            }
        }
    }
}