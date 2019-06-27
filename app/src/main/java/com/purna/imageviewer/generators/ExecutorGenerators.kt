package com.purna.imageviewer.generators

import com.purna.base.Dispatchers
import com.purna.base.Executors
import com.purna.base.creational.BaseGenerator
import com.purna.base.creational.single
import com.purna.imageviewer.async.AppExecutor
import com.purna.imageviewer.async.CommonExecutor
import com.purna.imageviewer.async.IoExecutor
import com.purna.imageviewer.async.MainThreadExecutor
import kotlinx.coroutines.asCoroutineDispatcher

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

/**
 * Genrators for [AppExecutor] or [Executors] or [Dispatchers] instances
 */

val commonExecutorGenerator: BaseGenerator<AppExecutor> = single { CommonExecutor() }

val ioExecutorGenerator: BaseGenerator<AppExecutor> = single { IoExecutor() }

val mainExecutorGenerator: BaseGenerator<AppExecutor> = single { MainThreadExecutor() }

val appExecutorsProvider: BaseGenerator<Executors> = single {
    Executors(
        mainExecutor = mainExecutorGenerator.getInstance().executor,
        ioExecutor = ioExecutorGenerator.getInstance().executor,
        commonExecutor = commonExecutorGenerator.getInstance().executor
    )
}

val appDispatchersProvider: BaseGenerator<Dispatchers> = single {
    Dispatchers(
        mainDispatcher = appExecutorsProvider.getInstance().mainExecutor.asCoroutineDispatcher(),
        ioDispatcher = appExecutorsProvider.getInstance().ioExecutor.asCoroutineDispatcher(),
        commonDispatcher = appExecutorsProvider.getInstance().commonExecutor.asCoroutineDispatcher()
    )
}