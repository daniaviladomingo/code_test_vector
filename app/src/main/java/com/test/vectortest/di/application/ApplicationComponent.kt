package com.test.vectortest.di.application

import android.content.Context
import com.test.domain.interactors.GetCachedUsersSingleUseCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.vectortest.AppApplication
import com.test.vectortest.di.application.module.*
import com.test.vectortest.utils.schedulers.IScheduleProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    DataSourceModule::class,
    CacheDataSourceModule::class,
    NetworkDataSourceModule::class,
    MapperModule::class,
    SchedulerModule::class,
    UseCaseModule::class])

interface ApplicationComponent {

    @ForApplication
    fun provideContext(): Context

    fun provideSchedule(): IScheduleProvider

    fun provideGetUsersSingleUseCase(): GetUsersSingleUseCase

    fun provideGetUserCachedSingleUseCase(): GetCachedUsersSingleUseCase

    companion object {
        fun init(appApplication: AppApplication): ApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(appApplication))
                .build()
    }
}