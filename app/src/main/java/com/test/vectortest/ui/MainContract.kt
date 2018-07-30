package com.test.vectortest.ui

import com.test.domain.model.User
import com.test.vectortest.base.BaseView
import com.test.vectortest.base.ScopePresenter

interface MainContract {
    interface IView : BaseView {
        fun showUsers(users: List<User>)
    }

    interface IPresenter : ScopePresenter {
        fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int)
        fun initializeList()
        fun loadUsersFromCache(idUser: Int)
    }
}