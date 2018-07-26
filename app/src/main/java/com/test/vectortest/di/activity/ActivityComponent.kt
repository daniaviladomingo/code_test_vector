package com.test.vectortest.di.activity

import com.test.vectortest.di.activity.module.ActivityModule
import com.test.vectortest.di.application.ApplicationComponent
import com.test.vectortest.ui.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])

interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    companion object {
        fun init(daggerActivity: DaggerActivity, applicationComponent: ApplicationComponent): ActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(ActivityModule(daggerActivity))
                .build()
    }
}