package demo.nitin.tumblr_android_demo.base.remote

import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.UiBlog
import io.reactivex.Single

interface Network {
    fun getPosts(offset: Int): Single<Posts>
    fun getBlogs(offset: Int): Single<Blogs>
    fun getUserLikes(offset: Int): Single<Posts>
    fun getBlogPosts(offset: Int, blog: UiBlog): Single<Posts>
}