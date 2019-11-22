package demo.nitin.tumblr_android_demo.features.likes

import demo.nitin.tumblr_android_demo.base.Posts
import io.reactivex.Single

interface LikesRepo {
    fun getUserLikes(offset: Int): Single<Posts>
    fun getNewLikes(offset: Int): Single<Posts>
}