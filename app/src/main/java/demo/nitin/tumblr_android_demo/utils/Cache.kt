package demo.nitin.tumblr_android_demo.utils

import java.util.concurrent.ConcurrentHashMap

/**
 * An interface to define a Caching mechanism
 *
 * @author - Nitin Dasari
 * @since - 11/18/2019
 */
interface CacheInterface {

    fun size(): Int

    operator fun set(key: Any, value: Any)

    operator fun get(key: Any): Any?

    fun remove(key: Any): Any?

    fun clear()
}

/**
 * An utility class to save or retrieve items in Cache
 * @see [CacheInterface]
 */
object Cache : CacheInterface {

    var cache = ConcurrentHashMap<Any, Any?>()

    override fun size(): Int = cache.size

    override fun set(key: Any, value: Any) {
        cache[key] = value
    }

    override fun get(key: Any): Any? = cache[key]

    override fun remove(key: Any): Any? = cache.remove(key)

    override fun clear() = cache.clear()

    fun put(key: Any, value: Any) = set(key, value)

    fun containsKey(key: Any) = cache.containsKey(key)

    fun clearAllExcept(vararg keys: Any) {
        cache.keys.retainAll(keys)
    }

    fun getAllKeys(): String = cache.keys.joinToString()
}
