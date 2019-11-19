package demo.nitin.tumblr_android_demo.features.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import demo.nitin.tumblr_android_demo.R
import demo.nitin.tumblr_android_demo.features.base.Posts
import demo.nitin.tumblr_android_demo.utils.UiState
import kotlinx.android.synthetic.main.dashboard_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var postsAdapter: PostsAdapter
    private val dashboardViewModel: DashboardViewModel by viewModel()

    companion object {
        fun newInstance() = DashboardFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dashboardViewModel.getDashboardPosts(20).observe(this, Observer {
            when (it) {
                is UiState.Loading -> {
                    progress_bar?.visibility = View.VISIBLE
                }
                is UiState.Error -> {
                    progress_bar?.visibility = View.GONE
                    Toast.makeText(this.requireActivity(), it.message ?: "Error loading data", Toast.LENGTH_SHORT)
                        .show()
                }
                is UiState.Success -> {
                    progress_bar?.visibility = View.GONE
                    it.data?.let { posts -> updatePosts(posts) }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        swipe_layout?.setOnRefreshListener {
            Log.d(this::class.qualifiedName, "onRefresh called from SwipeRefreshLayout")
        }
    }

    private fun updatePosts(posts: Posts) {
        layoutManager = LinearLayoutManager(activity)
        post_list?.layoutManager = layoutManager
        post_list?.hasFixedSize()
        postsAdapter = PostsAdapter(this, posts)
        post_list?.adapter = postsAdapter
    }
}
