package demo.nitin.tumblr_android_demo.features.following

import demo.nitin.tumblr_android_demo.base.Blogs
import io.reactivex.Single

interface FollowingRepo {
    fun getBlogs(): Single<Blogs>
    fun getNewBlogs(): Single<Blogs>
}