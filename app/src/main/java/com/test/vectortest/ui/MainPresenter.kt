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
    var since = 0

    override fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition >= totalItemCount) {
            if (isRequestInProgress) return
            isRequestInProgress = true
            loadMoreUser()
        }
    }

    override fun init() {}

    override fun initializeList() {
        loadMoreUser()
    }

    override fun loadUsersFromCache(idUser: Int) {
        addDisposable(getCachedUserSingleUserCase.execute(idUser).observeOn(scheduleProvider.io())
                .subscribeOn(scheduleProvider.ui())
                .subscribe({ users ->
                    since = users.last().id
                    view.showUsers(users)
                }) {})
    }

    private fun loadMoreUser() {
        addDisposable(getUsersSingleUseCase.execute(since)
                .observeOn(scheduleProvider.io())
                .subscribeOn(scheduleProvider.ui())
                .subscribe({ users ->
                    isRequestInProgress = false
                    since = users.last().id
                    view.showUsers(users)
                }) {
                    isRequestInProgress = false
                })
    }
}