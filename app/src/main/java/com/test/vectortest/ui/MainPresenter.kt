package com.test.vectortest.ui

import com.test.domain.interactors.GetCachedUserSingleUserCase
import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.vectortest.base.BasePresenter
import com.test.vectortest.utils.schedulers.IScheduleProvider

class MainPresenter(private val getUsersSingleUseCase: GetUsersSingleUseCase,
                    private val getCachedUserSingleUserCase: GetCachedUserSingleUserCase,
                    private val scheduleProvider: IScheduleProvider,
                    view: MainContract.IView) : BasePresenter<MainContract.IView>(view), MainContract.IPresenter {

    private var isRequestInProgress = false
    private var since = 0

    override fun init() {
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
        addDisposable(getCachedUserSingleUserCase.execute(lastIdUserLoaded).observeOn(scheduleProvider.io())
                .subscribeOn(scheduleProvider.ui())
                .subscribe({ users ->
                    since = users.last().id
                    view.addUsers(users)
                    view.scrollListToItem(lastVisibleItemPosition)
                }) {})
    }

    private fun loadMoreUser() {
        view.showProgress("Loading...")
        addDisposable(getUsersSingleUseCase.execute(since)
                .observeOn(scheduleProvider.io())
                .subscribeOn(scheduleProvider.ui())
                .subscribe({ users ->
                    view.dismissProgress()
                    isRequestInProgress = false
                    since = users.last().id
                    view.addUsers(users)
                }) {
                    view.dismissProgress()
                    isRequestInProgress = false
                })
    }
}