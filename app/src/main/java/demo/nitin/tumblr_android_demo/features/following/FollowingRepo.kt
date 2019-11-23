package demo.nitin.tumblr_android_demo.features.following

import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.UiBlog
import io.reactivex.Single

interface FollowingRepo {
    fun getBlogs(offset: Int): Single<Blogs>
    fun getNewBlogs(offset: Int): Single<Blogs>
    fun getBlogPosts(offset: Int, blog: UiBlog): Single<Posts>
    fun getNewBlogPosts(offset: Int, blog: UiBlog): Single<Posts>
}