package com.test.vectortest.di.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.vectortest.AppApplication
import com.test.vectortest.di.createActivityComponent

abstract class DaggerActivity: AppCompatActivity() {

    private var activityComponent: ActivityComponent? = null
        get() = field ?: createActivityComponent(this, appApplication)

    private val appApplication: AppApplication
        get() = (application as AppApplication)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(activityComponent!!)
    }

    protected abstract fun inject(activityComponent: ActivityComponent)
}