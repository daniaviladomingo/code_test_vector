package com.test.vectortest.di.application.module

import android.app.Application
import android.content.Context
import com.test.vectortest.di.application.ForApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    @ForApplication
    internal fun provideContext(): Context {
        return application
    }
}