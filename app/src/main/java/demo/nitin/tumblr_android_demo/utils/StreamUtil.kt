package demo.nitin.tumblr_android_demo.utils

import demo.nitin.tumblr_android_demo.base.UiBlog
import demo.nitin.tumblr_android_demo.base.UiPost
import io.reactivex.subjects.PublishSubject

class StreamFactory {
    fun <T> hotStream(): PublishSubject<T> {
        return PublishSubject.create()
    }
}

/**
 * Hot stream that distributes the Posts response to corresponding observers
 * @author - Nitin Dasari
 * @since - 11/20/2019
 */
object PostsStreamFactory {
    val postsStream: PublishSubject<ArrayList<UiPost>> = StreamFactory().hotStream()

    fun postFetchSuccess(newPosts: ArrayList<UiPost>) {
        postsStream.onNext(newPosts)
    }
}

/**
 * Hot stream that distributes the Blogs response to corresponding observers
 * @author - Nitin Dasari
 * @since - 11/20/2019
 */
object BlogsStreamFactory {
    val blogsStream: PublishSubject<ArrayList<UiBlog>> = StreamFactory().hotStream()

    fun postFetchSuccess(newBlogs: ArrayList<UiBlog>) {
        blogsStream.onNext(newBlogs)
    }
}
