package demo.nitin.tumblr_android_demo.features.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import demo.nitin.tumblr_android_demo.R
import demo.nitin.tumblr_android_demo.features.base.PostViewHolder
import demo.nitin.tumblr_android_demo.features.base.Posts

class PostsAdapter(private val fragment: DashboardFragment, private val posts: Posts) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var uiPosts = posts.uiPosts

    fun setNewData(posts: Posts) {
        uiPosts = posts.uiPosts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(fragment.requireActivity()).inflate(R.layout.post_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return uiPosts.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiPost = uiPosts.getOrNull(position)
        uiPost?.let {
            (holder as? PostViewHolder)?.setPosts(uiPost)
        }
    }
}
