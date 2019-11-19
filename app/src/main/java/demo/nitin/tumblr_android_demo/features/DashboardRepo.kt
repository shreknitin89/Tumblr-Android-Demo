package demo.nitin.tumblr_android_demo.features

import demo.nitin.tumblr_android_demo.features.base.Posts
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface DashboardRepo {
    fun getPosts(offset: Int): Single<Posts>
}