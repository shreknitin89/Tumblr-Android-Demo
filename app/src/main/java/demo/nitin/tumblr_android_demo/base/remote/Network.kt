package demo.nitin.tumblr_android_demo.base.remote

import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.Posts
import io.reactivex.Single

interface Network {
    fun getPosts(offset: Int): Single<Posts>
    fun getBlogs(): Single<Blogs>
    fun getUserLikes(offset: Int): Single<Posts>
}