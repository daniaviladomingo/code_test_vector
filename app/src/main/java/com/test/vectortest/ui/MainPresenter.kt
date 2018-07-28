package com.test.vectortest.ui

import com.test.domain.interactors.GetUsersSingleUseCase
import com.test.vectortest.base.BasePresenter
import com.test.vectortest.utils.schedulers.IScheduleProvider

class MainPresenter(private val getUsersSingleUseCase: GetUsersSingleUseCase,
                    private val scheduleProvider: IScheduleProvider,
                    view: MainContract.IView) : BasePresenter<MainContract.IView>(view), MainContract.IPresenter {

    override fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + 5 >= totalItemCount) {
            loadMoreUser()
        }
    }

    override fun init() {
        loadMoreUser()
    }

    private fun loadMoreUser() {
        addDisposable(getUsersSingleUseCase.execute()
                .observeOn(scheduleProvider.io())
                .subscribeOn(scheduleProvider.ui())
                .subscribe { users ->
                    view.showUsers(users)
                })
    }
}