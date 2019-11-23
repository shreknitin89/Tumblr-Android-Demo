package demo.nitin.tumblr_android_demo.features.likes

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

class LikesViewModel(private val repository: LikesRepo) : PostsViewModel, ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getNewPosts(offset: Int, blog: UiBlog?) {
        compositeDisposable.add(repository.getNewLikes(offset).performOnComputation().subscribe { posts ->
            PostsStreamFactory.postFetchSuccess(posts.uiPosts)
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getUserLikes(offset: Int): LiveData<UiState<Posts>> {
        return repository.getUserLikes(offset).performOnComputation()
            .toLiveData(compositeDisposable)
    }
}
