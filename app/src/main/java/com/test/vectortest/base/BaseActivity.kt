package com.test.vectortest.base

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.test.vectortest.base.progress.IShowProgress
import com.test.vectortest.di.activity.DaggerActivity

abstract class BaseActivity : DaggerActivity(), BaseView {

    lateinit var showProgress: IShowProgress
    var toolbar: Toolbar? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getLayoutId().run {
            if (this != 0) {
                setContentView(this)
            }
        }

        initializeToolbar()
    }

    private fun initializeToolbar() {}

    override fun onStart() {
        super.onStart()
        getScopePresenter().init()
    }

    override fun onStop() {
        getScopePresenter().onStop()
        super.onStop()
    }

    abstract fun getScopePresenter(): ScopePresenter

    abstract fun getLayoutId(): Int

    override fun showProgress(message: String) {
        showProgress.showProgress(message)
    }

    override fun dismissProgress() {
        showProgress.dismissProgress()
    }
}