package com.test.vectortest.di.application.module

import com.test.vectortest.utils.schedulers.IScheduleProvider
import com.test.vectortest.utils.schedulers.ScheduleProviderImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulerModule {
    @Provides
    @Singleton
    fun provideScheduleProvider(): IScheduleProvider = ScheduleProviderImp()
}