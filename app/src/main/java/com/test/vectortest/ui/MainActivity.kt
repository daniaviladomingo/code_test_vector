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
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.IView {

    @Inject
    lateinit var presenter: MainContract.IPresenter

    private val userList = mutableListOf<User>()
    private val adapter = UserAdapter(userList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user_list.adapter = adapter

        setupScrollListener()

        savedInstanceState?.run {
            presenter.restore(getInt(LAST_USER_ID_LOADED), getInt(FIRST_USER_VISIBLE))
        } ?: presenter.loadUsers()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (userList.isNotEmpty()) {
            outState.putInt(LAST_USER_ID_LOADED, userList.last().id)
            outState.putInt(FIRST_USER_VISIBLE, (user_list.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition())
        }
    }

    override fun getScopePresenter(): ScopePresenter = presenter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun addUsers(users: List<User>) {
        userList.addAll(users)
        adapter.notifyDataSetChanged()
    }

    override fun scrollListToItem(scrollToItem: Int) {
        user_list.scrollToPosition(scrollToItem)
    }

    private fun setupScrollListener() {
        user_list.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    (layoutManager as LinearLayoutManager).run {
                        presenter.listScrolled(childCount, findFirstVisibleItemPosition(), itemCount)
                    }
                }
            })
        }
    }

    override fun checkAgain(): () -> Unit = { presenter.loadUsers() }

    override fun tryAgain(): () -> Unit = { presenter.loadUsers() }

    companion object {
        private const val LAST_USER_ID_LOADED: String = "last_user_id_loaded"
        private const val FIRST_USER_VISIBLE: String = "first_user_visible"
    }
}
