package com.purna.baseandroid

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

data class Dispatchers(
    val mainDispatcher: CoroutineDispatcher,
    val ioDispatcher: CoroutineDispatcher,
    val commonDispatcher: CoroutineDispatcher
)