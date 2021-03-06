package demo.nitin.tumblr_android_demo.features.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import demo.nitin.tumblr_android_demo.R
import demo.nitin.tumblr_android_demo.base.UiBlog
import demo.nitin.tumblr_android_demo.utils.BlogsStreamFactory
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dashboard_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class FollowingFragment : Fragment() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var blogsAdapter: BlogsAdapter
    private val disposable = CompositeDisposable()
    private val blogsViewModel: FollowingViewModel by viewModel()

    companion object {
        fun newInstance() = FollowingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManager = LinearLayoutManager(this.requireActivity())
        blogsAdapter = BlogsAdapter(this, blogsViewModel, ArrayList())
        post_list?.layoutManager = layoutManager
        post_list?.visibility = View.VISIBLE
        post_list?.hasFixedSize()
        post_list?.adapter = blogsAdapter

        blogsViewModel.getUserFollowingBlogs(0).observe(this, Observer {
            when (it) {
                is UiState.Loading -> {
                    // Do nothing -> Ideally show a progress
                }
                is UiState.Error -> {
                    Toast.makeText(
                        this.requireActivity(),
                        it.message ?: "Error loading data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UiState.Success -> {
                    it.data?.let { blogs -> updatePosts(blogs.uiBlogs) }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        disposable.add(BlogsStreamFactory.blogsStream.subscribe {
            updatePosts(it)
        })
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun updatePosts(blogs: ArrayList<UiBlog>) {
        blogsAdapter.setNewData(blogs)
    }
}
