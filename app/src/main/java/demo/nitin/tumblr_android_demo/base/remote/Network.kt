package demo.nitin.tumblr_android_demo.base.remote

import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.Likes
import demo.nitin.tumblr_android_demo.base.Posts
import io.reactivex.Single

interface Network {
    fun getPosts(): Single<Posts>
    fun getBlogs(): Single<Blogs>
    fun getUserLikes(): Single<Likes>
}