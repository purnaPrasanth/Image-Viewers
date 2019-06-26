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

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    abstract fun setListeners()

    abstract fun initUI()
}