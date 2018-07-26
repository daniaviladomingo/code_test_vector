package com.test.vectortest.di.application

import android.content.Context
import com.test.data.network.INetworkDataSource
import com.test.data.network.model.UserApi
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import com.test.domain.repository.IRepository
import com.test.vectortest.AppApplication
import com.test.vectortest.di.application.module.*
import com.test.vectortest.utils.schedulers.IScheduleProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    DataSourceModule::class,
    MapperModule::class,
    NetworkDataSourceModule::class,
    SchedulerModule::class,
    UseCaseModule::class])

interface ApplicationComponent {

    fun provideContext(): Context

    fun provideDataSource(): IRepository

    fun provideNetworkMapper(): Mapper<UserApi, User>

    fun provideNetworkDataSource(): INetworkDataSource

    fun provideSchedule(): IScheduleProvider

    fun provideGetUsersSingleUseCase(): GetUsersSingleUseCase

    companion object {
        fun init(appApplication: AppApplication): ApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(appApplication))
                .build()
    }
}