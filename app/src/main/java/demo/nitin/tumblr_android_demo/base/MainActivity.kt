package demo.nitin.tumblr_android_demo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import demo.nitin.tumblr_android_demo.R
import demo.nitin.tumblr_android_demo.features.dashboard.DashboardFragment
import demo.nitin.tumblr_android_demo.features.following.FollowingFragment
import demo.nitin.tumblr_android_demo.features.likes.LikesFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Base activity defining the UI components and fragment navigation
 * @author - Nitin Dasari
 * @since - 11/17/2019
 */
class MainActivity : AppCompatActivity(), FragNavController.RootFragmentListener {

    companion object {
        const val DASHBOARD = 0
        const val FOLLOWING = 1
        const val LIKES = 2
        const val MAX_NUM_TABS = 3
    }

    private lateinit var fragNavController: FragNavController

    override val numberOfRootFragments: Int =
        MAX_NUM_TABS

    override fun getRootFragment(index: Int): Fragment {
        // return the root fragment for each tab
        return when (index) {
            DASHBOARD -> DashboardFragment.newInstance()
            FOLLOWING -> FollowingFragment.newInstance()
            LIKES -> LikesFragment.newInstance()
            else -> DashboardFragment.newInstance()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragNavController =
            FragNavController(supportFragmentManager, R.id.content_container).apply {
                rootFragmentListener = this@MainActivity
                initialize(DASHBOARD, savedInstanceState)
            }

        initNavigationBar()
    }

    override fun onBackPressed() {
        if(!fragNavController.isRootFragment) {
            popFragment()
        } else {
            super.onBackPressed()
        }
    }

    fun pushFragment(fragment: Fragment) {
        fragNavController.pushFragment(fragment)
    }

    fun popFragment() {
        fragNavController.popFragment()
    }

    private fun initNavigationBar() {
        navigation_bar?.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.dashboard -> {
                    fragNavController.switchTab(DASHBOARD)
                }
                R.id.following -> {
                    fragNavController.switchTab(FOLLOWING)
                }
                R.id.likes -> {
                    fragNavController.switchTab(LIKES)
                }
            }
            true
        }
    }
}
