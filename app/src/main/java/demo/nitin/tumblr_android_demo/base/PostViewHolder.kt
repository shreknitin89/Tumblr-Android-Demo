package demo.nitin.tumblr_android_demo.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import demo.nitin.tumblr_android_demo.R
import demo.nitin.tumblr_android_demo.utils.ImageLoadingUtil
import java.util.Locale
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_layout.*

class PostViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    fun setPosts(uiPost: UiPost, fragment: Fragment) {
        post_title?.text = uiPost.blogName
        tags?.text = String.format(Locale.US, fragment.getString(R.string.tags), uiPost.tags)
        ImageLoadingUtil.loadImage(fragment.requireContext(), uiPost.photo, post_image)
    }
}
