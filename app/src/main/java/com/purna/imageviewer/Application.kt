package com.purna.imageviewer

import android.app.Application
import com.purna.baseandroid.Dispatchers
import com.purna.baseandroid.Executors
import com.purna.baseandroid.creational.BaseGenerator
import com.purna.baseandroid.creational.single
import com.purna.imageviewer.async.*

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/
class ImageViewerApplication : Application() {

    companion object {
        val appExecutorsProvider: BaseGenerator<Executors> by lazy {
            single {
                Executors(
                    mainExecutor = mainExecutorGenerator.getInstance().executor,
                    ioExecutor = ioExecutorGenerator.getInstance().executor,
                    commonExecutor = commonExecutorGenerator.getInstance().executor
                )
            }
        }

        val appDispatchersProvider: BaseGenerator<Dispatchers> by lazy {
            single {
                Dispatchers(
                    mainDispatcher = mainDispatcherGenerator.getInstance(),
                    ioDispatcher = ioDispatcherGenerator.getInstance(),
                    commonDispatcher = commonDispatcherGenerator.getInstance()
                )
            }
        }
    }

}