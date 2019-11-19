package demo.nitin.tumblr_android_demo.features.base

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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