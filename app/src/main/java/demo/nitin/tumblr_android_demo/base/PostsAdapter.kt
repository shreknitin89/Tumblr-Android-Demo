package demo.nitin.tumblr_android_demo.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import demo.nitin.tumblr_android_demo.R

class PostsAdapter(private val fragment: Fragment, var uiPosts: ArrayList<UiPost>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setNewData(uiPost: ArrayList<UiPost>) {
        uiPosts = uiPost
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(fragment.requireActivity())
            .inflate(R.layout.post_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return uiPosts.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiPost = uiPosts.getOrNull(position)
        uiPost?.let {
            (holder as? PostViewHolder)?.setPosts(uiPost, fragment)
        }
    }
}
