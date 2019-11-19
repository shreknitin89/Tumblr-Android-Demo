package demo.nitin.tumblr_android_demo.utils

/**
 * Generic class to maintain states in responses
 * @url https://developer.android.com/jetpack/docs/guide?source=post_page---------------------------#addendum
 */
sealed class UiState<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null) {
    class Success<T>(data: T) : UiState<T>(Status.SUCCESS, data)
    class Loading<T>(data: T? = null) : UiState<T>(Status.LOADING, data)
    class Error<T>(message: String, data: T? = null) : UiState<T>(Status.ERROR, data, message)
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
