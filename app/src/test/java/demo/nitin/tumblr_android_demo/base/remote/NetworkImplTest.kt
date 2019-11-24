package demo.nitin.tumblr_android_demo.base.remote

import com.tumblr.jumblr.JumblrClient
import com.tumblr.jumblr.exceptions.JumblrException
import com.tumblr.jumblr.types.Blog
import com.tumblr.jumblr.types.Post
import demo.nitin.tumblr_android_demo.base.Blogs
import demo.nitin.tumblr_android_demo.base.Posts
import demo.nitin.tumblr_android_demo.base.UiBlog
import demo.nitin.tumblr_android_demo.base.UiPost
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
class NetworkImplTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    private val jumblrClient = mock<JumblrClient>()
    lateinit var network: Network

    @Before
    fun setUp() {
        network = NetworkImpl(jumblrClient)
    }

    @Test
    fun testGetDashboardPosts() {
        `when`(jumblrClient.userDashboard()).thenReturn(ArrayList<Post>())
        val testObserver = TestObserver<Posts>()
        val result = network.getPosts(Mockito.anyInt())
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.uiPosts, ArrayList<UiPost>())
    }

    @Test
    fun testGetDashboardPostsError() {
        `when`(jumblrClient.userDashboard()).thenThrow(JumblrException::class.java)
        val testObserver = TestObserver<Posts>()
        val result = network.getPosts(Mockito.anyInt())
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.uiPosts, ArrayList<UiPost>())
        assertEquals(listResult.uiPosts.size, 0)
    }

    @Test
    fun testGetLikePosts() {
        `when`(jumblrClient.userLikes()).thenReturn(ArrayList<Post>())
        val testObserver = TestObserver<Posts>()
        val result = network.getUserLikes(Mockito.anyInt())
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.uiPosts, ArrayList<UiPost>())
    }

    @Test
    fun testGetLikesPostsError() {
        `when`(jumblrClient.userLikes()).thenThrow(JumblrException::class.java)
        val testObserver = TestObserver<Posts>()
        val result = network.getUserLikes(Mockito.anyInt())
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.uiPosts, ArrayList<UiPost>())
        assertEquals(listResult.uiPosts.size, 0)
    }

    @Test
    fun testGetBlogs() {
        `when`(jumblrClient.userFollowing()).thenReturn(ArrayList<Blog>())
        val testObserver = TestObserver<Blogs>()
        val result = network.getBlogs(Mockito.anyInt())
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.uiBlogs, ArrayList<UiBlog>())
    }

    @Test
    fun testGetBlogsError() {
        `when`(jumblrClient.userFollowing()).thenReturn(ArrayList<Blog>())
        val testObserver = TestObserver<Blogs>()
        val result = network.getBlogs(Mockito.anyInt())
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.uiBlogs, ArrayList<UiBlog>())
        assertEquals(listResult.uiBlogs.size, 0)
    }

    @Test
    fun testGetBlogPosts() {
        `when`(jumblrClient.blogInfo(Mockito.anyString())).thenReturn(mock())
        val testObserver = TestObserver<Posts>()
        val result = network.getBlogPosts(Mockito.anyInt(), UiBlog("", "", "", 0, 0, 0))
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.uiPosts, ArrayList<UiPost>())
    }

    @Test
    fun testGetBlogPostsError() {
        val blog: Blog = mock()
        `when`(jumblrClient.blogInfo(Mockito.anyString())).thenReturn(blog)
        `when`(blog.posts()).thenThrow(JumblrException::class.java)
        val testObserver = TestObserver<Posts>()
        val result = network.getBlogPosts(Mockito.anyInt(), UiBlog("", "", "", 0, 0, 0))
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.uiPosts, ArrayList<UiPost>())
        assertEquals(listResult.uiPosts.size, 0)
    }
}