package demo.nitin.tumblr_android_demo.features.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import demo.nitin.tumblr_android_demo.base.Dashboard
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.UiPost
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
class DashboardViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dashboardObserver: Observer<UiState<Posts>> = mock()

    private val dashboardRepo: DashboardRepo = mock()

    lateinit var viewModel: DashboardViewModel

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = DashboardViewModel(dashboardRepo)
    }

    @Test
    fun testErrorFetchingPosts() {
        `when`(dashboardRepo.getPosts(0)).thenReturn(Single.error(Throwable()))
        viewModel.getDashboardPosts(0).observeForever(dashboardObserver)
        assert(viewModel.getDashboardPosts(0).value == UiState.Success(Dashboard(ArrayList())))
    }

    @Test
    fun testIfViewModelGiveBackLiveData() {
        `when`(dashboardRepo.getPosts(0)).thenReturn(Single.just(Dashboard(ArrayList())))
        viewModel.getDashboardPosts(0).observeForever(dashboardObserver)
        assertNotNull(viewModel.getDashboardPosts(0))
        assert(viewModel.getDashboardPosts(0).value is UiState<Posts>)
    }

    @Test
    fun testIfViewModelCallsRepo() {
        `when`(dashboardRepo.getPosts(0)).thenReturn(Single.just(Dashboard(ArrayList())))
        viewModel.getDashboardPosts(0).observeForever(dashboardObserver)
        assertNotNull(viewModel.getDashboardPosts(0))
        assert(viewModel.getDashboardPosts(0).value is UiState<Posts>)
    }

    @Test
    fun testCount() {
        val posts = ArrayList<UiPost>()
        for (i in 0..20) {
            posts.add(UiPost("Name - $i", "title - $i", "", "", ""))
        }
        `when`(dashboardRepo.getPosts(20)).thenReturn(Single.just(Dashboard(posts)))
        viewModel.getDashboardPosts(20).observeForever(dashboardObserver)
        assert(viewModel.getDashboardPosts(20).value?.data == posts)
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
