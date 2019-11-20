package demo.nitin.tumblr_android_demo.features.dashboard

import demo.nitin.tumblr_android_demo.base.Posts
import io.reactivex.Single

interface DashboardRepo {
    fun getPosts(): Single<Posts>
    fun getNewPosts(): Single<Posts>
}