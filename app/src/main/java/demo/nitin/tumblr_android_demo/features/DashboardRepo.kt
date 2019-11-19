package demo.nitin.tumblr_android_demo.features

import io.reactivex.Flowable

interface DashboardRepo {
    fun getPosts(offset: Int): Flowable<Any>
}