package com.purna.base.creational

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/

/**
 * Base Generator Wrapper for any instance of type [T]
 * @param T The Type of Instance to be provided by this generator
 *
 * see [single] & [factory]
 */

interface BaseGenerator<T> {
    fun getInstance(): T
}

/**
 * Singleton Wrapper for any instance of type [T]
 * @param T The Type of Instance to be provided by this generator
 *
 * return the same instance for all the requests
 */
class single<T>(generatingBlock: () -> T) : BaseGenerator<T> {
    private val _instance: T by lazy { generatingBlock() }

    override fun getInstance() = _instance
}

/**
 * Factory Wrapper for any instance of type [T]
 * @param T The Type of Instance to be provided by this generator
 *
 * return the different instance for each request
 */
class factory<T>(val generatingBlock: () -> T) : BaseGenerator<T> {
    override fun getInstance() = generatingBlock()
}