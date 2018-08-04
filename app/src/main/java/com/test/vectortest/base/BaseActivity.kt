package com.test.vectortest.base

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.test.vectortest.base.progress.IProgressView
import com.test.vectortest.di.activity.DaggerActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerActivity(), BaseView {

    @Inject
    lateinit var progress: IProgressView
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getLayoutId() == 0) {
            throw RuntimeException("Invalid Layout ID")
        }

        setContentView(getLayoutId())

        initializeToolbar()
    }

    private fun initializeToolbar() {}

    abstract fun getLayoutId(): Int

    override fun showProgress(message: String) {
        progress.showProgress(message)
    }

    override fun dismissProgress() {
        progress.dismissProgress()
    }
}