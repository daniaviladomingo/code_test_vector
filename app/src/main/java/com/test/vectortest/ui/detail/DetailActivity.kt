package com.test.vectortest.ui.detail

import com.test.domain.model.UserExtra
import com.test.vectortest.R
import com.test.vectortest.base.BaseActivity
import com.test.vectortest.base.ScopePresenter
import com.test.vectortest.di.activity.ActivityComponent
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailContract.IView {

    @Inject
    lateinit var presenter: DetailContract.IPresenter

    override fun getScopePresenter(): ScopePresenter = presenter

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun checkAgain(): () -> Unit {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun tryAgain(): () -> Unit {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun showExtra(userExtra: UserExtra) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
