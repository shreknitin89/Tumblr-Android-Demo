package demo.nitin.tumblr_android_demo.features.following

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.UiBlog
import demo.nitin.tumblr_android_demo.mock
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.Observable
import io.reactivex.Single
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FollowingViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val followingObserver: Observer<UiState<Blogs>> = mock()

    private val followingRepo: FollowingRepo = mock()

    lateinit var viewModel: FollowingViewModel

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = FollowingViewModel(followingRepo)
    }

    @Test
    fun testErrorFetchingBlogs() {
        `when`(followingRepo.getBlogs(0)).thenReturn(Single.error(Throwable()))
        viewModel.getUserFollowingBlogs(0).observeForever(followingObserver)
        assert(viewModel.getUserFollowingBlogs(0).value == UiState.Success(Blogs(ArrayList())))
    }

    @Test
    fun testIfViewModelGiveBackLiveData() {
        `when`(followingRepo.getBlogs(0)).thenReturn(Single.just(Blogs(ArrayList())))
        viewModel.getUserFollowingBlogs(0).observeForever(followingObserver)
        assertNotNull(viewModel.getUserFollowingBlogs(0))
        assert(viewModel.getUserFollowingBlogs(0).value is UiState<Blogs>)
    }

    @Test
    fun testIfViewModelCallsRepo() {
        `when`(followingRepo.getBlogs(0)).thenReturn(Single.just(Blogs(ArrayList())))
        viewModel.getUserFollowingBlogs(0).observeForever(followingObserver)
        assertNotNull(viewModel.getUserFollowingBlogs(0))
        assert(viewModel.getUserFollowingBlogs(0).value is UiState<Blogs>)
    }

    @Test
    fun testCount() {
        val uiBlogs = ArrayList<UiBlog>()
        for (i in 0..20) {
            uiBlogs.add(UiBlog("Name - $i", "title - $i", "", i, i, i))
        }
        `when`(followingRepo.getBlogs(20)).thenReturn(Single.just(Blogs(uiBlogs)))
        viewModel.getUserFollowingBlogs(20).observeForever(followingObserver)
        assert(viewModel.getUserFollowingBlogs(20).value?.data == Blogs(uiBlogs))
    }

    @Test
    fun testClear() {
        viewModel.compositeDisposable.add(Observable.just("").subscribe())
        assertEquals(1, viewModel.compositeDisposable.size())
        viewModel.compositeDisposable.clear()
        assertEquals(0, viewModel.compositeDisposable.size())
    }

    @After
    fun tearDown() {
    }
}