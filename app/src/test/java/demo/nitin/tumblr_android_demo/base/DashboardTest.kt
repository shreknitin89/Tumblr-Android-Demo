package demo.nitin.tumblr_android_demo.base

import org.junit.Assert.assertEquals
import org.junit.Test

class UserActivityTest {

    @Test
    fun testEmptyPosts() {
        val testClass = Dashboard(ArrayList())
        assertEquals(testClass.uiPosts, emptyList<UiPost>())
    }

    @Test
    fun testNonEmptyPosts() {
        val posts = ArrayList<UiPost>()
        for (i in 0..9) {
            posts.add(UiPost("name $i", "title $i", "", "", ""))
        }
        val testClass = Dashboard(posts)
        assertEquals(testClass.uiPosts.size, 10)
        assertEquals(testClass.uiPosts[7], UiPost("name 7", "title 7", "", "", ""))
    }

    @Test
    fun testEmptyLikes() {
        val testClass = Likes(ArrayList())
        assertEquals(testClass.uiPosts, emptyList<UiPost>())
    }

    @Test
    fun testNonEmptyLikes() {
        val posts = ArrayList<UiPost>()
        for (i in 0..9) {
            posts.add(UiPost("name $i", "title $i", "", "", ""))
        }
        val testClass = Likes(posts)
        assertEquals(testClass.uiPosts.size, 10)
        assertEquals(testClass.uiPosts[7], UiPost("name 7", "title 7", "", "", ""))
    }

    @Test
    fun testEmptyBlogs() {
        val testClass = Blogs(ArrayList())
        assertEquals(testClass.uiBlogs, emptyList<UiPost>())
    }

    @Test
    fun testNonEmptyBlogs() {
        val blogs = ArrayList<UiBlog>()
        for (i in 0..9) {
            blogs.add(UiBlog("name $i", "title $i", "", i, i, i))
        }
        val testClass = Blogs(blogs)
        assertEquals(testClass.uiBlogs.size, 10)
        assertEquals(testClass.uiBlogs[7], UiBlog("name 7", "title 7", "", 7, 7, 7))
    }
}