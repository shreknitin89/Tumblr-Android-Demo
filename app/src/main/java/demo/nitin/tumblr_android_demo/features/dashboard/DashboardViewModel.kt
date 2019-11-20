package demo.nitin.tumblr_android_demo.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.extensions.performOnComputation
import demo.nitin.tumblr_android_demo.extensions.toLiveData
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.disposables.CompositeDisposable

class DashboardViewModel(private val repository: DashboardRepo) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getDashboardPosts(): LiveData<UiState<Posts>> {
        return repository.getPosts().performOnComputation().toLiveData(compositeDisposable)
    }

    fun getNewDashboardPosts(): LiveData<UiState<Posts>> {
        return repository.getPosts().performOnComputation().toLiveData(compositeDisposable)
    }
}
