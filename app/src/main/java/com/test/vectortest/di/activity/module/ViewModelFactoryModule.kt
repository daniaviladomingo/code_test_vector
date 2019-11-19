package com.test.vectortest.di.activity.module

import androidx.lifecycle.ViewModelProvider
import com.test.domain.interactors.GetCachedUserSingleUserCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.vectortest.di.activity.ActivityScope
import com.test.vectortest.ui.ViewModelFactory
import com.test.vectortest.utils.schedulers.IScheduleProvider
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {
    @Provides
    @ActivityScope
    fun provideViewModelFactory(getUsersSingleUseCase: GetUsersSingleUseCase, getCachedUserSingleUserCase: GetCachedUserSingleUserCase, scheduleProvider: IScheduleProvider): ViewModelProvider.Factory = ViewModelFactory(getUsersSingleUseCase, getCachedUserSingleUserCase, scheduleProvider)
}