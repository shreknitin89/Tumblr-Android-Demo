package demo.nitin.tumblr_android_demo.features.likes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import demo.nitin.tumblr_android_demo.R
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.PostsAdapter
import demo.nitin.tumblr_android_demo.base.UiPost
import demo.nitin.tumblr_android_demo.utils.PostsStreamFactory
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dashboard_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class LikesFragment : Fragment() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var postsAdapter: PostsAdapter
    private val disposable = CompositeDisposable()
    private val likesViewModel: LikesViewModel by viewModel()

    companion object {
        fun newInstance() = LikesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManager = LinearLayoutManager(this.requireActivity())
        postsAdapter = PostsAdapter(this, likesViewModel, ArrayList())
        post_list?.layoutManager = layoutManager
        post_list?.visibility = View.VISIBLE
        post_list?.hasFixedSize()
        post_list?.adapter = postsAdapter

        likesViewModel.getUserLikes(0).observe(this, Observer {
            refreshData(it)
        })
    }

    override fun onResume() {
        super.onResume()

        disposable.add(PostsStreamFactory.postsStream.subscribe {
            updateLikes(it)
        })
    }

    private fun refreshData(posts: UiState<Posts>) {
        when (posts) {
            is UiState.Loading -> {
                // Do nothing -> Ideally show a progress
            }
            is UiState.Error -> {
                Toast.makeText(
                    this.requireActivity(),
                    posts.message ?: "Error loading data",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is UiState.Success -> {
                posts.data?.let { updateLikes(it.uiPosts) }
            }
        }
    }

    private fun updateLikes(likes: ArrayList<UiPost>) {
        postsAdapter.setNewData(likes)
    }
}
