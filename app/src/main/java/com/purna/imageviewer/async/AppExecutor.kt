package com.purna.imageviewer.async

import java.util.concurrent.Executor

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/
interface AppExecutor {
    val executor: Executor
}