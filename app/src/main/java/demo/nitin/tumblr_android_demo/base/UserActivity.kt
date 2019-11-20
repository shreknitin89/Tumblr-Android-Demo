package demo.nitin.tumblr_android_demo.base

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * The file contains a set of data classes that the app is going to perform operations on
 * @author - Nitin Dasari
 * @since - 11/19/2019
 */
@Parcelize
data class Posts(
    val uiPosts: ArrayList<UiPost>
) : Parcelable

@Parcelize
data class UiPost(
    val blogName: String,
    val title: String,
    val description: String,
    val photo: String,
    val tags: String
) : Parcelable

@Parcelize
data class Likes(
    val uiPosts: ArrayList<UiPost>
) : Parcelable

@Parcelize
data class Blogs(
    val uiBlogs: ArrayList<UiBlog>
) : Parcelable

@Parcelize
data class UiBlog(
    val name: String,
    val title: String,
    val description: String,
    val posts: Int = 0,
    val likes: Int = 0, val followers: Int = 0
) : Parcelable