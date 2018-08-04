package com.test.vectortest.ui

import android.arch.lifecycle.MutableLiveData
import com.test.domain.interactors.GetCachedUserSingleUserCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.domain.model.User
import com.test.vectortest.base.BaseViewModel
import com.test.vectortest.ui.data.Resource
import com.test.vectortest.ui.data.ResourceState
import com.test.vectortest.utils.schedulers.IScheduleProvider

class MainViewModel(private val getUsersSingleUseCase: GetUsersSingleUseCase,
                    private val getCachedUserSingleUserCase: GetCachedUserSingleUserCase,
                    private val scheduleProvider: IScheduleProvider) : BaseViewModel() {

    private var isRequestInProgress = false
    private var since = 0

    var usersLiveData = MutableLiveData<Resource<List<User>>>()
    var positionToScroll = MutableLiveData<Int>()

    fun init() {
        loadMoreUser()
    }

    fun restore(lastIdUserLoaded: Int, lastVisibleItemPosition: Int) {
        loadUsersFromCache(lastIdUserLoaded, lastVisibleItemPosition)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition >= totalItemCount) {
            if (isRequestInProgress) return
            isRequestInProgress = true
            loadMoreUser()
        }
    }

    private fun loadMoreUser() {
        usersLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        addDisposable(getUsersSingleUseCase.execute(since)
                .observeOn(scheduleProvider.io())
                .subscribeOn(scheduleProvider.ui())
                .subscribe({ users ->
                    isRequestInProgress = false
                    since = users.last().id
                    if (users.isNotEmpty()) {
                        usersLiveData.postValue(Resource(ResourceState.SUCCESS, users, null))
                    } else {
                        usersLiveData.postValue(Resource(ResourceState.EMPTY, null, null))
                    }
                }) {
                    isRequestInProgress = false
                    usersLiveData.postValue(Resource(ResourceState.ERROR, null, it.message))
                })
    }

    private fun loadUsersFromCache(lastIdUserLoaded: Int, lastVisibleItemPosition: Int) {
        addDisposable(getCachedUserSingleUserCase.execute(lastIdUserLoaded).observeOn(scheduleProvider.io())
                .subscribeOn(scheduleProvider.ui())
                .subscribe({ users ->
                    since = users.last().id
                    usersLiveData.postValue(Resource(ResourceState.SUCCESS, users, null))
                    positionToScroll.postValue(lastVisibleItemPosition)
                }) {
                    usersLiveData.postValue(Resource(ResourceState.ERROR, null, it.message))
                })
    }
}