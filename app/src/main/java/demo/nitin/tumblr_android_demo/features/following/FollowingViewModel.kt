package demo.nitin.tumblr_android_demo.features.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.PostsViewModel
import demo.nitin.tumblr_android_demo.base.UiBlog
import demo.nitin.tumblr_android_demo.extensions.performOnComputation
import demo.nitin.tumblr_android_demo.extensions.toLiveData
import demo.nitin.tumblr_android_demo.utils.BlogsStreamFactory
import demo.nitin.tumblr_android_demo.utils.PostsStreamFactory
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.disposables.CompositeDisposable

class FollowingViewModel constructor(private val repository: FollowingRepo) : PostsViewModel,
    ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getUserFollowingBlogs(offset: Int): LiveData<UiState<Blogs>> {
        return repository.getBlogs(offset).performOnComputation().toLiveData(compositeDisposable)
    }

    fun getNewBlogs(offset: Int) {
        compositeDisposable.add(repository.getNewBlogs(offset)
            .performOnComputation()
            .subscribe { blogs ->
                BlogsStreamFactory.postFetchSuccess(blogs.uiBlogs)
            })
    }

    fun getBlogPosts(offset: Int, blog: UiBlog): LiveData<UiState<Posts>> {
        return repository.getBlogPosts(offset, blog).performOnComputation()
            .toLiveData(compositeDisposable)
    }

    override fun getNewPosts(offset: Int, blog: UiBlog?) {
        blog?.let {
            compositeDisposable.add(
                repository.getNewBlogPosts(
                    offset,
                    blog
                ).performOnComputation().subscribe { posts ->
                    PostsStreamFactory.postFetchSuccess(posts.uiPosts)
                })
        }
    }
}
