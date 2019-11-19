package demo.nitin.tumblr_android_demo.features.remote

import demo.nitin.tumblr_android_demo.features.models.Posts
import io.reactivex.Single

interface Network {
    fun getPosts(offset: Int): Single<Posts>
}