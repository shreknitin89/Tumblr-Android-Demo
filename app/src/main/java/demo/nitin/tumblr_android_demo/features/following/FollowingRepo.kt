package demo.nitin.tumblr_android_demo.features.following

import demo.nitin.tumblr_android_demo.base.Blogs
import io.reactivex.Single

interface FollowingRepo {
    fun getBlogs(offset: Int): Single<Blogs>
    fun getNewBlogs(offset: Int): Single<Blogs>
}