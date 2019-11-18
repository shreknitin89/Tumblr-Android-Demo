package demo.nitin.tumblr_android_demo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tumblr.jumblr.JumblrClient
import com.tumblr.jumblr.types.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DashboardViewModel(private val client: JumblrClient) : ViewModel() {

    val single = Single.create<User> { emitter ->
        val user = client.user()
        val dashboard = client.userDashboard()
        val following = client.userFollowing()
        val likes = client.userLikes()
        user?.let { emitter.onSuccess(user) } ?: kotlin.run { emitter.tryOnError(Throwable()) }
    }

    val disposable =
        single.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
            .subscribe({ user ->
                val name = user.name
                Log.d("User", name)
            }, { error -> Log.e("Error fetching user", error?.message ?: "") })
}
