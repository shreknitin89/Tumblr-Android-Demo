package demo.nitin.tumblr_android_demo.features.dashboard

import demo.nitin.tumblr_android_demo.base.Dashboard
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.UiPost
import demo.nitin.tumblr_android_demo.base.remote.Network
import demo.nitin.tumblr_android_demo.base.remote.RxImmediateSchedulerRule
import demo.nitin.tumblr_android_demo.base.remote.mock
import demo.nitin.tumblr_android_demo.extensions.fullyTypedName
import demo.nitin.tumblr_android_demo.utils.Cache
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(JUnit4::class)
class DashboardRepoImplTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    private val network: Network = mock()
    private val cache: Cache = mock()
    lateinit var repo: DashboardRepo

    @Before
    fun setup() {
        repo = DashboardRepoImpl(network)
    }

    @Test
    fun test1getNewPosts() {
        val networkPosts = arrayListOf(UiPost("Network Post", "", "", "", ""))
        `when`(network.getPosts(Mockito.anyInt())).thenReturn(Single.just(Dashboard(networkPosts)))
        `when`(cache[Dashboard::class.fullyTypedName()] as? Posts).thenReturn(null)
        val result = repo.getPosts(Mockito.anyInt())
        val testObserver = TestObserver<Posts>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(listResult, Dashboard(networkPosts))
    }

    @Test
    fun test2getPosts() {
        `when`(network.getPosts(Mockito.anyInt())).thenReturn(Single.just(Dashboard(ArrayList())))
        `when`(cache[Dashboard::class.fullyTypedName()] as? Posts).thenReturn(null)
        val result = repo.getPosts(Mockito.anyInt())
        val observer = TestObserver<Posts>()
        result.subscribe(observer)
        observer.assertComplete()
        observer.assertNoErrors()
        observer.assertValueCount(1)
        val listResult = observer.values()[0]
        assertEquals(listResult, Dashboard(ArrayList()))
    }
}
