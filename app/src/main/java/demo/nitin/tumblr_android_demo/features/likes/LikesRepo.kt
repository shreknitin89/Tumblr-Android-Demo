package demo.nitin.tumblr_android_demo.features.likes

import demo.nitin.tumblr_android_demo.base.Likes
import io.reactivex.Single

interface LikesRepo {
    fun getUserLikes(): Single<Likes>
    fun getNewLikes(): Single<Likes>
}