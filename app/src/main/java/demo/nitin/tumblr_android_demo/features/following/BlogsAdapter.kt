package demo.nitin.tumblr_android_demo.features.following

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import demo.nitin.tumblr_android_demo.R
import demo.nitin.tumblr_android_demo.base.MainActivity
import demo.nitin.tumblr_android_demo.base.UiBlog
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.blog_layout.*
import kotlinx.android.synthetic.main.progress_layout.*
import java.util.Locale

class BlogsAdapter(
    private val fragment: Fragment,
    val viewModel: FollowingViewModel,
    var blogs: ArrayList<UiBlog>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val BLOG = 1
        const val PROGRESS = 2
    }

    fun setNewData(uiBlogs: ArrayList<UiBlog>) {
        val oldSize = blogs.size
        blogs.addAll(uiBlogs)
        val newSize = blogs.size
        notifyItemRangeChanged(oldSize, newSize - oldSize)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < blogs.size) BLOG else PROGRESS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == BLOG) {
            val view = LayoutInflater.from(fragment.requireActivity())
                .inflate(R.layout.blog_layout, parent, false)
            BlogViewHolder(view)
        } else {
            val view = LayoutInflater.from(fragment.requireActivity())
                .inflate(R.layout.progress_layout, parent, false)
            ProgressViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (blogs.isNotEmpty() && blogs.size % 20 == 0) blogs.size + 1 // 1 for the progress bar
        else if (blogs.size % 20 > 0) blogs.size
        else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == BLOG) {
            val blog = blogs.getOrNull(position)
            blog?.let {
                (holder as? BlogViewHolder)?.bind(blog)
            }
        } else {
            (holder as? ProgressViewHolder)?.bind()
        }
    }

    private inner class BlogViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(blog: UiBlog) {
            blog_name?.text = blog.name
            blog_title?.text = blog.title
            blog_description?.text = blog.description
            blog_posts?.text =
                String.format(Locale.US, fragment.getString(R.string.posts_count), blog.posts)
            blog_likes?.text =
                String.format(Locale.US, fragment.getString(R.string.likes_count), blog.likes)
            blog_followers?.text =
                String.format(
                    Locale.US,
                    fragment.getString(R.string.followers_count),
                    blog.followers
                )

            blog_row?.setOnClickListener {
                // push fragment
                (fragment.requireActivity() as? MainActivity)?.pushFragment(BlogPostsFragment.newInstance(blog))
            }
        }
    }

    private inner class ProgressViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind() {
            progressbar?.visibility = View.VISIBLE
            viewModel.getNewBlogs(blogs.size)
        }
    }
}