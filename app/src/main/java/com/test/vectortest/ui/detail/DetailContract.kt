package com.test.vectortest.ui.detail

import com.test.domain.model.UserExtra
import com.test.vectortest.base.BaseView
import com.test.vectortest.base.ScopePresenter

interface DetailContract {
    interface IView : BaseView {
        fun showExtra(userExtra: UserExtra)
    }

    interface IPresenter : ScopePresenter {
        fun loadExtra(userName: String)
    }
}