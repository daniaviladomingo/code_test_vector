package com.test.vectortest.di.activity.module

import android.content.Context
import com.test.domain.interactors.GetCachedUsersSingleUseCase
import com.test.domain.interactors.GetUserExtraSingleUseCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.vectortest.ui.main.MainActivity
import com.test.vectortest.di.activity.DaggerActivity
import com.test.vectortest.di.activity.ActivityScope
import com.test.vectortest.di.activity.ForActivity
import com.test.vectortest.ui.detail.DetailActivity
import com.test.vectortest.ui.detail.DetailContract
import com.test.vectortest.ui.detail.DetailPresenter
import com.test.vectortest.ui.main.MainContract
import com.test.vectortest.ui.main.MainPresenter
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
    fun provideMainPresenter(getUsersSingleUseCase: GetUsersSingleUseCase, getCachedUserSingleUserCase: GetCachedUsersSingleUseCase, scheduleProvider: IScheduleProvider) : MainContract.IPresenter =
            MainPresenter(getUsersSingleUseCase, getCachedUserSingleUserCase, scheduleProvider, daggerActivity as MainActivity)

    @Provides
    @ActivityScope
    fun provideDetailPresenter(getUserExtraSingleUseCase: GetUserExtraSingleUseCase, scheduleProvider: IScheduleProvider) : DetailContract.IPresenter =
            DetailPresenter(getUserExtraSingleUseCase, scheduleProvider, daggerActivity as DetailActivity)
}