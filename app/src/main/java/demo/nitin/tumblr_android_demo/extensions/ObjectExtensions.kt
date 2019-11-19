package demo.nitin.tumblr_android_demo.extensions

import kotlin.reflect.KClass
import kotlin.reflect.full.starProjectedType

inline fun <reified T : Any> KClass<T>.fullyTypedName(): String {
    return T::class.starProjectedType.classifier.toString()
}

inline fun <reified T : Any> KClass<T>.uuid(): Int {
    return T::class.starProjectedType.classifier.hashCode()
}
