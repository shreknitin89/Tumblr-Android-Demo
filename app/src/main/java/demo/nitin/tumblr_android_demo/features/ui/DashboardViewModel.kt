package demo.nitin.tumblr_android_demo.features.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import demo.nitin.tumblr_android_demo.extensions.performOnComputation
import demo.nitin.tumblr_android_demo.extensions.toLiveData
import demo.nitin.tumblr_android_demo.features.DashboardRepo
import demo.nitin.tumblr_android_demo.features.base.Posts
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.disposables.CompositeDisposable

class DashboardViewModel(private val repository: DashboardRepo) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getDashboardPosts(offset: Int): LiveData<UiState<Posts>> {
        return repository.getPosts(offset).performOnComputation().toLiveData(compositeDisposable)
    }
}
