package demo.nitin.tumblr_android_demo.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.PostsViewModel
import demo.nitin.tumblr_android_demo.base.UiBlog
import demo.nitin.tumblr_android_demo.extensions.performOnComputation
import demo.nitin.tumblr_android_demo.extensions.toLiveData
import demo.nitin.tumblr_android_demo.utils.PostsStreamFactory
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.disposables.CompositeDisposable

class DashboardViewModel(private val repository: DashboardRepo) : PostsViewModel, ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getNewPosts(offset: Int, blog: UiBlog?) {
        compositeDisposable.add(repository.getPosts(offset)
            .performOnComputation()
            .subscribe { posts ->
                PostsStreamFactory.postFetchSuccess(posts.uiPosts)
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getDashboardPosts(offset: Int): LiveData<UiState<Posts>> {
        return repository.getPosts(offset).performOnComputation().toLiveData(compositeDisposable)
    }
}
