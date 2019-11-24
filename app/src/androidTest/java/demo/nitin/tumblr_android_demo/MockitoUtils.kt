package demo.nitin.tumblr_android_demo

import org.mockito.Mockito

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)