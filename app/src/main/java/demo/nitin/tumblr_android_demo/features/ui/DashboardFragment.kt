package demo.nitin.tumblr_android_demo.features.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import demo.nitin.tumblr_android_demo.R
import kotlinx.android.synthetic.main.dashboard_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var postsAdapter: PostsAdapter
    val dashboardViewModel: DashboardViewModel by viewModel()

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
            val data = it
            Log.d("Data", data?.message ?: "")
        })
    }

    override fun onResume() {
        super.onResume()
        layoutManager = LinearLayoutManager(activity)

        post_list?.layoutManager = layoutManager
        post_list?.hasFixedSize()
        post_list?.adapter = PostsAdapter()

        swipe_layout?.setOnRefreshListener {
            Log.d(this::class.qualifiedName,"onRefresh called from SwipeRefreshLayout")
            updatePosts()
        }
    }

    private fun updatePosts() {

    }
}
