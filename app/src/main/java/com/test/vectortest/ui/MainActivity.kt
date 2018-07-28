package com.test.vectortest.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.test.domain.model.User
import com.test.vectortest.R
import com.test.vectortest.base.BaseActivity
import com.test.vectortest.base.ScopePresenter
import com.test.vectortest.di.activity.ActivityComponent
import com.test.vectortest.ui.adapter.UserAdapter
import com.test.vectortest.utils.log
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.IView {

    @Inject
    lateinit var presenter: MainContract.IPresenter

    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user_list.adapter = adapter

        setupScrollListener()
    }

    override fun getScopePresenter(): ScopePresenter = presenter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun showUsers(users: List<User>) {
        runOnUiThread {
            adapter.submitList(users)
        }
    }

    private fun setupScrollListener() {
        user_list.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    (layoutManager as LinearLayoutManager).run {
                        "itemCount: $itemCount".log()
                        "childCount: $childCount".log()
                        "findLastVisibleItemPosition: ${findLastVisibleItemPosition()}".log()
                        presenter.listScrolled(childCount, findLastVisibleItemPosition(), itemCount)
                    }
                }
            })
        }
    }
}
