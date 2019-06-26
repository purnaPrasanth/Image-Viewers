package com.purna.base

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/

/**
 * Dispatchers to be used across app
 * @param mainDispatcher The dispatcher for Operations on Main Thread
 * @param ioDispatcher The dispatcher for IO Operations
 * @param commonDispatcher The Dispatcher for Common Computational Operations
 *
 * Usually created from [Executors]
 */

data class Dispatchers(
    val mainDispatcher: CoroutineDispatcher,
    val ioDispatcher: CoroutineDispatcher,
    val commonDispatcher: CoroutineDispatcher
)