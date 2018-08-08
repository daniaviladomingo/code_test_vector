package com.test.vectortest.di.activity

import android.content.Context
import com.test.vectortest.di.activity.module.ActivityModule
import com.test.vectortest.di.application.ApplicationComponent
import com.test.vectortest.ui.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])

interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    @ForActivity
    fun provideContext(): Context

    companion object {
        fun init(daggerActivity: DaggerActivity, applicationComponent: ApplicationComponent): ActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(ActivityModule(daggerActivity))
                .build()
    }
}