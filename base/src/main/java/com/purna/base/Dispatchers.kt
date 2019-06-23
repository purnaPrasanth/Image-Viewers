package com.purna.base

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/

data class Dispatchers(
    val mainDispatcher: CoroutineDispatcher,
    val ioDispatcher: CoroutineDispatcher,
    val commonDispatcher: CoroutineDispatcher
)