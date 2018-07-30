package com.test.vectortest.ui

import com.test.domain.model.User
import com.test.vectortest.base.BaseView
import com.test.vectortest.base.ScopePresenter

interface MainContract {
    interface IView : BaseView {
        fun showUsers(users: List<User>, scrollToItem: Int = -1)
    }

    interface IPresenter : ScopePresenter {
        fun init(lastIdUserLoaded: Int, positionFirstUserVisible: Int)
        fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int)
    }
}