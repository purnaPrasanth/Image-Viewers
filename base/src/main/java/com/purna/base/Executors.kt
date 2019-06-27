package com.purna.base

import java.util.concurrent.Executor

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/

/**
 * Executors to be used across App
 * @param mainDispatcher The Executor for Operations on Main Thread
 * @param ioDispatcher The Executor for IO Operations
 * @param commonDispatcher The Executor for Common Computational Operations
 *
 * Usually Used to create [Dispatchers]
 */
data class Executors(
    val mainExecutor: Executor,
    val ioExecutor: Executor,
    val commonExecutor: Executor
)