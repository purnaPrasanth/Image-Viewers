package com.purna.imageviewer.async

import com.purna.baseandroid.util.threadFactory
import java.util.concurrent.Executor
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

class IoExecutor : AppExecutor {
    override val executor: Executor
        get() = _executor

    private val _executor = ThreadPoolExecutor(
        0,
        Integer.MAX_VALUE,
        15,
        TimeUnit.SECONDS,
        SynchronousQueue<Runnable>(),
        threadFactory("App IO Executor", false)
    )
}