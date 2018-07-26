package com.test.vectortest

import android.app.Application
import com.test.vectortest.di.application.ApplicationComponent
import com.test.vectortest.di.createApplicationComponent

class AppApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = createApplicationComponent(this)
    }
}