package demo.nitin.tumblr_android_demo.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import demo.nitin.tumblr_android_demo.utils.ImageLoadingUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_layout.*

class PostViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    fun setPosts(uiPost: UiPost, fragment: Fragment) {
        post_title?.text = uiPost.blogName
        tags?.text = uiPost.tags
        ImageLoadingUtil.loadImage(fragment.requireContext(), uiPost.photo, post_image)
    }
}