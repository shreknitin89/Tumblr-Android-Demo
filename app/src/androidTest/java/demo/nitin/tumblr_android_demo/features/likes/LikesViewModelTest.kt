package demo.nitin.tumblr_android_demo.features.likes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import demo.nitin.tumblr_android_demo.base.Likes
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.UiPost
import demo.nitin.tumblr_android_demo.mock
import demo.nitin.tumblr_android_demo.utils.UiState
import io.reactivex.Observable
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LikesViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val likesObserver: Observer<UiState<Posts>> = mock()

    private val likesRepo: LikesRepo = mock()

    lateinit var viewModel: LikesViewModel

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = LikesViewModel(likesRepo)
    }

    @Test
    fun testErrorFetchingBlogs() {
        Mockito.`when`(likesRepo.getUserLikes(0)).thenReturn(Single.error(Throwable()))
        viewModel.getUserLikes(0).observeForever(likesObserver)
        assert(viewModel.getUserLikes(0).value == UiState.Success(Likes(ArrayList())))
    }

    @Test
    fun testIfViewModelGiveBackLiveData() {
        Mockito.`when`(likesRepo.getUserLikes(0)).thenReturn(Single.just(Likes(ArrayList())))
        viewModel.getUserLikes(0).observeForever(likesObserver)
        Assert.assertNotNull(viewModel.getUserLikes(0))
        assert(viewModel.getUserLikes(0).value is UiState<Posts>)
    }

    @Test
    fun testIfViewModelCallsRepo() {
        Mockito.`when`(likesRepo.getUserLikes(0)).thenReturn(Single.just(Likes(ArrayList())))
        viewModel.getUserLikes(0).observeForever(likesObserver)
        Assert.assertNotNull(viewModel.getUserLikes(0))
        assert(viewModel.getUserLikes(0).value is UiState<Posts>)
    }

    @Test
    fun testCount() {
        val posts = ArrayList<UiPost>()
        for (i in 0..20) {
            posts.add(UiPost("Name - $i", "title - $i", "", "", ""))
        }
        Mockito.`when`(likesRepo.getUserLikes(20)).thenReturn(Single.just(Likes(posts)))
        viewModel.getUserLikes(20).observeForever(likesObserver)
        assert(viewModel.getUserLikes(20).value?.data == Likes(posts))
    }

    @Test
    fun testClear() {
        viewModel.compositeDisposable.add(Observable.just("").subscribe())
        TestCase.assertEquals(1, viewModel.compositeDisposable.size())
        viewModel.compositeDisposable.clear()
        TestCase.assertEquals(0, viewModel.compositeDisposable.size())
    }

    @After
    fun tearDown() {
    }
}
