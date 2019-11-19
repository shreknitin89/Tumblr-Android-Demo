package demo.nitin.tumblr_android_demo.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Nitin Dasari
 * @since 11/18/2019
 **/

/**
 * Extension function to convert an Observable into a LiveData by subscribing to it.
 **/
fun <T> Observable<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<UiState<T>> {
    val data = MutableLiveData<UiState<T>>()
    compositeDisposable.add(subscribe(
        {
            // On Next
            data.postValue(UiState.Success(it))
        },
        {
            // On Error
            data.postValue(UiState.Error("Error : ${it.message}"))
        },
        {
            // On Complete
        }, {
            // On Subscribe
            data.postValue(UiState.Loading())
        })
    )
    return data
}

/**
 * Extension function to convert a Flowable into a LiveData by subscribing to it.
 **/
fun <T> Flowable<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<UiState<T>> {
    val data = MutableLiveData<UiState<T>>()
    compositeDisposable.add(subscribe(
        {
            // On Next
            data.postValue(UiState.Success(it))
        },
        {
            // On Error
            data.postValue(UiState.Error("Error : ${it.message}"))
        },
        {
            // On Complete
        }, {
            // On Subscribe
            data.postValue(UiState.Loading())
        })
    )
    return data
}

/**
 * Extension function to convert a Single into a LiveData by subscribing to it.
 **/
fun <T> Single<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<UiState<T>> {
    return this.toObservable().toLiveData(compositeDisposable)
}
