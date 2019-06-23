package com.purna.base

import java.util.concurrent.Executor

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/
data class Executors(
    val mainExecutor: Executor,
    val ioExecutor: Executor,
    val commonExecutor: Executor
)