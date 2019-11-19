package demo.nitin.tumblr_android_demo.features.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_layout.*

class PostViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun setPosts(uiPost: UiPost) {
            post_title?.text = uiPost.blogName
            tags?.text = uiPost.tags
        }
}