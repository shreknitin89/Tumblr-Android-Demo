package demo.nitin.tumblr_android_demo.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import demo.nitin.tumblr_android_demo.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.progress_layout.*

class PostsAdapter(
    private val fragment: Fragment,
    private val viewModel: PostsViewModel,
    var uiPosts: ArrayList<UiPost>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val POST = 1
        const val PROGRESS = 2
    }

    fun setNewData(uiPost: ArrayList<UiPost>) {
        val oldSize = uiPosts.size
        uiPosts.addAll(uiPost)
        val newSize = uiPosts.size
        notifyItemRangeChanged(oldSize, newSize - oldSize)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < uiPosts.size) POST else PROGRESS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == POST) {
            val view = LayoutInflater.from(fragment.requireActivity())
                .inflate(R.layout.post_layout, parent, false)
            PostViewHolder(view)
        } else {
            val view = LayoutInflater.from(fragment.requireActivity())
                .inflate(R.layout.progress_layout, parent, false)
            ProgressViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (uiPosts.isNotEmpty()) uiPosts.size + 1 // 1 for the progress bar
        else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == POST) {
            val uiPost = uiPosts.getOrNull(position)
            uiPost?.let {
                (holder as? PostViewHolder)?.setPosts(uiPost, fragment)
            }
        } else {
            (holder as? ProgressViewHolder)?.bind()
        }
    }

    private inner class ProgressViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind() {
            progressbar?.visibility = View.VISIBLE
            viewModel.getNewPosts(uiPosts.size)
        }
    }
}
