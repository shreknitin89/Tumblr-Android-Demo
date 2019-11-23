package demo.nitin.tumblr_android_demo.utils

import demo.nitin.tumblr_android_demo.base.UiPost
import io.reactivex.subjects.PublishSubject

/**
 * Hot stream that distributes the Posts response to corresponding observers
 * @author - Nitin Dasari
 * @since - 11/20/2019
 */
object PostsStreamFactory {
    val postsStream: PublishSubject<ArrayList<UiPost>> = PublishSubject.create()

    fun postFetchSuccess(newPosts: ArrayList<UiPost>) {
        postsStream.onNext(newPosts)
    }
}
