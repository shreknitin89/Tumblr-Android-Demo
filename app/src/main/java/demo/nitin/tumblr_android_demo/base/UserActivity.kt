package demo.nitin.tumblr_android_demo.base

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * The file contains a set of data classes that the app is going to perform operations on
 * @author - Nitin Dasari
 * @since - 11/19/2019
 */
interface Posts : Parcelable {
    val uiPosts: ArrayList<UiPost>
}

@Parcelize
data class Dashboard(
    override val uiPosts: ArrayList<UiPost>
) : Posts

@Parcelize
data class Likes(
    override val uiPosts: ArrayList<UiPost>
) : Posts

@Parcelize
data class UiPost(
    val blogName: String,
    val title: String,
    val description: String,
    val photo: String,
    val tags: String
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
    val posts: Int,
    val likes: Int,
    val followers: Int
) : Parcelable