package com.purna.imageviewer.async

import com.purna.baseandroid.creational.BaseGenerator
import com.purna.baseandroid.creational.single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

val commonExecutorGenerator: BaseGenerator<AppExecutor> = single { CommonExecutor() }

val ioExecutorGenerator: BaseGenerator<AppExecutor> = single { IoExecutor() }

val mainExecutorGenerator: BaseGenerator<AppExecutor> = single { MainThreadExecutor() }

val commonDispatcherGenerator: BaseGenerator<CoroutineDispatcher> =
    single { commonExecutorGenerator.getInstance().executor.asCoroutineDispatcher() }

val ioDispatcherGenerator: BaseGenerator<CoroutineDispatcher> =
    single { commonExecutorGenerator.getInstance().executor.asCoroutineDispatcher() }

val mainDispatcherGenerator: BaseGenerator<CoroutineDispatcher> =
    single { mainExecutorGenerator.getInstance().executor.asCoroutineDispatcher() }