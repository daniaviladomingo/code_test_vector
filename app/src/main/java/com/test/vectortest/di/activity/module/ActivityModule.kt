package com.test.vectortest.di.activity.module

import android.content.Context
import com.test.domain.interactors.GetCachedUserSingleUserCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.vectortest.ui.MainActivity
import com.test.vectortest.di.activity.DaggerActivity
import com.test.vectortest.di.activity.ActivityScope
import com.test.vectortest.di.activity.ForActivity
import com.test.vectortest.ui.MainContract
import com.test.vectortest.ui.MainPresenter
import com.test.vectortest.utils.schedulers.IScheduleProvider
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val daggerActivity: DaggerActivity) {

    @Provides
    @ActivityScope
    @ForActivity
    fun provideContext(): Context = daggerActivity

    @Provides
    @ActivityScope
    fun provideMainPresenter(getUsersSingleUseCase: GetUsersSingleUseCase, getCachedUserSingleUserCase: GetCachedUserSingleUserCase, scheduleProvider: IScheduleProvider) : MainContract.IPresenter =
        MainPresenter(getUsersSingleUseCase, getCachedUserSingleUserCase, scheduleProvider, daggerActivity as MainActivity)
}