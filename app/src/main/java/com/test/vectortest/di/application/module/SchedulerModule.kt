package com.test.vectortest.di.application.module

import com.test.vectortest.utils.schedulers.IScheduleProvider
import com.test.vectortest.utils.schedulers.ScheduleProviderImp
import dagger.Module
import dagger.Provides

@Module
class SchedulerModule {
    @Provides
    fun provideScheduleProvider(): IScheduleProvider = ScheduleProviderImp()
}