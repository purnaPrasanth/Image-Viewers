package com.purna.data.datasource

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * Base class for Result of a Network Call
 * @param T the data that this is holding
 */
sealed class Result<T> {
    abstract fun get(): T?
}

/**
 * Success implementation of Network Result [Result]
 * @param T data that this Success result holds
 */
data class Success<T>(val data: T) : Result<T>() {
    override fun get() = data
}

/**
 * Error implementation of Network Result [Result]
 * @param exception Error Details for Failure
 */
data class Error<T>(val exception: Exception) : Result<T>() {
    override fun get() = null
}