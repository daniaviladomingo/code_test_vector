package com.test.vectortest.di.activity.module

import android.app.ProgressDialog
import android.content.Context
import com.test.vectortest.base.progress.IProgressView
import com.test.vectortest.base.progress.ProgressViewImp
import com.test.vectortest.di.activity.ActivityScope
import com.test.vectortest.di.activity.ForActivity
import dagger.Module
import dagger.Provides

@Module
class ProgressModule {
    @Provides
    @ActivityScope
    fun provideProgressView(@ForActivity context: Context): IProgressView = ProgressViewImp(ProgressDialog(context))
}