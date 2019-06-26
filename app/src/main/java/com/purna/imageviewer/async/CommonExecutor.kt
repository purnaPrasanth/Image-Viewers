package com.purna.imageviewer.async

import android.os.AsyncTask
import java.util.concurrent.Executor

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

/**
 * Executor for Common Computational tasks
 */

class CommonExecutor : AppExecutor {
    override val executor: Executor
        get() = AsyncTask.THREAD_POOL_EXECUTOR
}