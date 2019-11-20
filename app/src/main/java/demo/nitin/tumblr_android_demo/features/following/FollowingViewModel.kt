package demo.nitin.tumblr_android_demo.features.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.extensions.performOnComputation
import demo.nitin.tumblr_android_demo.extensions.toLiveData
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.disposables.CompositeDisposable

class FollowingViewModel constructor(private val repository: FollowingRepo) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getUserFollowingBlogs(): LiveData<UiState<Blogs>> {
        return repository.getBlogs().performOnComputation().toLiveData(compositeDisposable)
    }

    fun getNewDashboardPosts(): LiveData<UiState<Blogs>> {
        return repository.getNewBlogs().performOnComputation().toLiveData(compositeDisposable)
    }
}