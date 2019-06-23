package com.purna.data.datasource

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
sealed class Result<T> {
    abstract fun get(): T?
}

data class Success<T>(val data: T) : Result<T>() {
    override fun get() = data
}

data class Error<T>(val exception: Exception) : Result<T>() {
    override fun get() = null
}