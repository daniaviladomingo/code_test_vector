package com.test.vectortest.di

import com.test.vectortest.AppApplication
import com.test.vectortest.di.activity.ActivityComponent
import com.test.vectortest.di.activity.DaggerActivity
import com.test.vectortest.di.application.ApplicationComponent

fun createApplicationComponent(appApplication: AppApplication): ApplicationComponent = ApplicationComponent.init(appApplication)

fun createActivityComponent(daggerActivity: DaggerActivity, appApplication: AppApplication): ActivityComponent = ActivityComponent.init(daggerActivity, appApplication.applicationComponent)