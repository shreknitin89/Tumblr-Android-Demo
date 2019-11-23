package demo.nitin.tumblr_android_demo.base

/**
 * This class defines the contract between different view models to get new posts. The posts can
 * either be from UserDashboard or UserLikes
 *
 * @author - Nitin Dasari
 * @since - 11/20/2019
 */
interface PostsViewModel {
    fun getNewPosts(offset: Int, blog: UiBlog? = null)
}
