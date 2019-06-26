package com.purna.baseandroid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.purna.base.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * Created by Purna on 2019-06-26 as a part of Image-Viewers
 **/

/**
 * Base Class for ViewModel
 *
 * This also extends [CoroutineScope], hence acts as a parent for all the coRoutines started in this activity scope or Lifecycle
 * @property job parent job for the coRoutines started in this scope
 */

open class BaseViewModel(protected val appDispatchers: Dispatchers, application: Application) :
    AndroidViewModel(application), CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + appDispatchers.commonDispatcher

    /**
     * Cancelling all the Async Operations stated in this scope
     */
    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}