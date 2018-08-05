package com.test.vectortest.di.activity.module

import android.content.Context
import com.test.vectortest.di.activity.ActivityScope
import com.test.vectortest.di.activity.DaggerActivity
import com.test.vectortest.di.activity.ForActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val daggerActivity: DaggerActivity) {

    @Provides
    @ActivityScope
    @ForActivity
    fun provideContext(): Context = daggerActivity
}