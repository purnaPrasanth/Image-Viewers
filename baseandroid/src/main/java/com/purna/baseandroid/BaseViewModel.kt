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
open class BaseViewModel(protected val appDispatchers: Dispatchers, application: Application) : AndroidViewModel(application), CoroutineScope {

    val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + appDispatchers.commonDispatcher

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}