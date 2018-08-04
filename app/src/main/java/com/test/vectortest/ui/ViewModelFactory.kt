package com.test.vectortest.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.test.domain.interactors.GetCachedUserSingleUserCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.vectortest.utils.schedulers.IScheduleProvider

class ViewModelFactory(private val getUsersSingleUseCase: GetUsersSingleUseCase,
                       private val getCachedUserSingleUserCase: GetCachedUserSingleUserCase,
                       private val scheduleProvider: IScheduleProvider) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(MainViewModel::class.java) -> MainViewModel(getUsersSingleUseCase, getCachedUserSingleUserCase, scheduleProvider)
                    else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")

                }
            } as T
}