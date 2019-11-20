package demo.nitin.tumblr_android_demo.features.likes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import demo.nitin.tumblr_android_demo.base.Likes
import demo.nitin.tumblr_android_demo.extensions.performOnComputation
import demo.nitin.tumblr_android_demo.extensions.toLiveData
import demo.nitin.tumblr_android_demo.features.likes.LikesRepo
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.disposables.CompositeDisposable

class LikesViewModel(private val repository: LikesRepo) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getUserLikes(): LiveData<UiState<Likes>> {
        return repository.getUserLikes().performOnComputation().toLiveData(compositeDisposable)
    }

    fun getNewLikedPosts(): LiveData<UiState<Likes>> {
        return repository.getNewLikes().performOnComputation().toLiveData(compositeDisposable)
    }
}
