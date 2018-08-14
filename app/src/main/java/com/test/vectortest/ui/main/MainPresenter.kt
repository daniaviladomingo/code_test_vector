package com.test.vectortest.ui.main

import com.test.domain.interactors.GetCachedUsersSingleUseCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.vectortest.base.BasePresenter
import com.test.vectortest.ui.utils.data.ResourceState
import com.test.vectortest.utils.schedulers.IScheduleProvider

class MainPresenter(private val getUsersSingleUseCase: GetUsersSingleUseCase,
                    private val getCachedUserSingleUserCase: GetCachedUsersSingleUseCase,
                    private val scheduleProvider: IScheduleProvider,
                    view: MainContract.IView) : BasePresenter<MainContract.IView>(view), MainContract.IPresenter {

    private var isRequestInProgress = false
    private var since = 0

    override fun loadUsers() {
        loadMoreUser()
    }

    override fun restore(lastIdUserLoaded: Int, lastVisibleItemPosition: Int) {
        loadUsersFromCache(lastIdUserLoaded, lastVisibleItemPosition)
    }

    override fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition >= totalItemCount) {
            if (isRequestInProgress) return
            isRequestInProgress = true
            loadMoreUser()
        }
    }

    private fun loadUsersFromCache(lastIdUserLoaded: Int, lastVisibleItemPosition: Int) {
        view.managementResourceState(ResourceState.LOADING)
        addDisposable(getCachedUserSingleUserCase.execute(lastIdUserLoaded)
                .observeOn(scheduleProvider.ui())
                .subscribeOn(scheduleProvider.io())
                .subscribe({ users ->
                    if (users.isNotEmpty()) {
                        view.managementResourceState(ResourceState.SUCCESS)
                        since = users.last().id
                        view.addUsers(users)
                        view.scrollListToItem(lastVisibleItemPosition)
                    } else {
                        view.managementResourceState(ResourceState.EMPTY)
                    }
                }) {
                    view.managementResourceState(ResourceState.ERROR, it.localizedMessage)
                })
    }

    private fun loadMoreUser() {
        view.managementResourceState(ResourceState.LOADING)
        addDisposable(getUsersSingleUseCase.execute(since)
                .observeOn(scheduleProvider.ui())
                .subscribeOn(scheduleProvider.io())
                .subscribe({ users ->
                    isRequestInProgress = false
                    if (users.isNotEmpty()) {
                        view.managementResourceState(ResourceState.SUCCESS)
                        since = users.last().id
                        view.addUsers(users)
                    } else {
                        view.managementResourceState(ResourceState.EMPTY)
                    }
                }) {
                    view.managementResourceState(ResourceState.ERROR, it.localizedMessage)
                    isRequestInProgress = false
                })
    }
}