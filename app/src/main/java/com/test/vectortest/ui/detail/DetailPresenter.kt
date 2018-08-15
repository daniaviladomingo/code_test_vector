package com.test.vectortest.ui.detail

import com.test.domain.interactors.GetUserExtraSingleUseCase
import com.test.vectortest.base.BasePresenter
import com.test.vectortest.utils.schedulers.IScheduleProvider

class DetailPresenter(private val getUserExtraSingleUseCase: GetUserExtraSingleUseCase,
                      private val scheduleProvider: IScheduleProvider,
                      view: DetailContract.IView) : BasePresenter<DetailContract.IView>(view), DetailContract.IPresenter {

    override fun loadExtra(userName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}