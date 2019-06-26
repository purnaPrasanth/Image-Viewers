package com.purna.baseandroid

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

/**
 * Base Class for activities across the app that heavily uses @see <a href="https://developer.android.com/topic/libraries/data-binding">Data Binding</a>
 *
 * @param BINDING the of view binding that this [AppCompatActivity] uses
 * @param layoutId the layout id for [setContentView]
 *
 * This also extends [CoroutineScope], hence acts as a parent for all the coRoutines started in this activity scope or Lifecycle
 * @property job parent job for the coRoutines started in this scope
 * @property binding Binding class made available for child classes for introducing effects
 */

abstract class BaseActivity<BINDING : ViewDataBinding>(@LayoutRes val layoutId: Int) : AppCompatActivity(),
    CoroutineScope {

    protected lateinit var binding: BINDING

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        initUI()
        setListeners()
    }

    /**
     * Cancelling all the Async Operations stated in this scope
     */
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    abstract fun setListeners()

    abstract fun initUI()
}