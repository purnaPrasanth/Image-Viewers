package com.purna.baseandroid.creational

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

interface BaseGenerator<T> {
    fun getInstance(): T
}

// TODO; Handle Params to Instances

class single<T>(generatingBlock: () -> T) : BaseGenerator<T> {
    private val _instance: T by lazy { generatingBlock() }

    override fun getInstance() = _instance
}

class factory<T>(val generatingBlock: () -> T) : BaseGenerator<T> {
    override fun getInstance() = generatingBlock()
}