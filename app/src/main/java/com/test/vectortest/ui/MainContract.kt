package com.test.vectortest.ui

import com.test.domain.model.User
import com.test.vectortest.base.BaseView
import com.test.vectortest.base.ScopePresenter

interface MainContract {
    interface IView : BaseView {
        fun addUsers(users: List<User>)
        fun scrollListToItem(scrollToItem: Int)
    }

    interface IPresenter : ScopePresenter {
        fun init()
        fun restore(lastIdUserLoaded: Int, lastVisibleItemPosition: Int)
        fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int)
    }
}