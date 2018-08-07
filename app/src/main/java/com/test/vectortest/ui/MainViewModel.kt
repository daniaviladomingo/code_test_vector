package com.test.vectortest.ui

import android.arch.lifecycle.MutableLiveData
import com.test.domain.interactors.GetCachedUserSingleUserCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.domain.model.User
import com.test.vectortest.base.BaseViewModel
import com.test.vectortest.ui.data.Resource
import com.test.vectortest.utils.log
import com.test.vectortest.utils.schedulers.IScheduleProvider

class MainViewModel(private val getUsersSingleUseCase: GetUsersSingleUseCase,
                    private val getCachedUserSingleUserCase: GetCachedUserSingleUserCase,
                    private val scheduleProvider: IScheduleProvider) : BaseViewModel() {

    private var isRequestInProgress = false
    private var since = 0

    var usersLiveData = MutableLiveData<Resource<List<User>>>()
    var positionToScroll = MutableLiveData<Int>()

    fun restore(lastIdUserLoaded: Int, lastVisibleItemPosition: Int) {
        loadUsersFromCache(lastIdUserLoaded, lastVisibleItemPosition)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition >= totalItemCount) {
            if (isRequestInProgress) return
            isRequestInProgress = true
            loadUsers()
        }
    }

    fun loadUsers() {
        "Load more since: $since".log("ccc")
        usersLiveData.value = Resource.loading()
        addDisposable(getUsersSingleUseCase.execute(since)
                .observeOn(scheduleProvider.ui())
                .subscribeOn(scheduleProvider.io())
                .subscribe({ users ->
                    isRequestInProgress = false
                    if (users.isNotEmpty()) {
                        since = users.last().id
                        var ids = ""
                        users.forEach { ids += "${it.id}," }
                        "Users: $ids".log("ccc")
                        usersLiveData.value = Resource.success(users)
                    } else {
                        usersLiveData.value = Resource.empty()
                    }
                }) {
                    isRequestInProgress = false
                    usersLiveData.value = Resource.error(it.localizedMessage)
                })
    }

    private fun loadUsersFromCache(lastIdUserLoaded: Int, lastVisibleItemPosition: Int) {
        "loadUsersFromCache".log("ccc")
        usersLiveData.value = Resource.loading()
        addDisposable(getCachedUserSingleUserCase.execute(lastIdUserLoaded)
                .observeOn(scheduleProvider.ui())
                .subscribeOn(scheduleProvider.io())
                .subscribe({ users ->
                    "actualizo".log("ccc")
                    since = users.last().id
                    usersLiveData.value = Resource.success(users)
                    positionToScroll.value = lastVisibleItemPosition
                }) {
                    usersLiveData.value = Resource.error(it.localizedMessage)
                })
    }
}