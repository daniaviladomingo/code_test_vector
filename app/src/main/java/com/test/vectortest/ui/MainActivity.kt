package com.test.vectortest.ui

import android.os.Bundle
import com.test.vectortest.R
import com.test.vectortest.base.BaseActivity
import com.test.vectortest.base.ScopePresenter
import com.test.vectortest.di.activity.ActivityComponent
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.IView {

    @Inject
    lateinit var presenter: MainContract.IPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getScopePresenter(): ScopePresenter = presenter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }
}
