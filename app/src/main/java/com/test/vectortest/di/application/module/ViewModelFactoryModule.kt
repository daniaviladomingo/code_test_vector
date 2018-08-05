package com.test.vectortest.di.application.module

import android.arch.lifecycle.ViewModelProvider
import com.test.domain.interactors.GetCachedUserSingleUserCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.vectortest.ui.ViewModelFactory
import com.test.vectortest.utils.schedulers.IScheduleProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(getUsersSingleUseCase: GetUsersSingleUseCase, getCachedUserSingleUserCase: GetCachedUserSingleUserCase, scheduleProvider: IScheduleProvider): ViewModelProvider.Factory = ViewModelFactory(getUsersSingleUseCase, getCachedUserSingleUserCase, scheduleProvider)
}