package com.test.vectortest.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.domain.model.User
import com.test.vectortest.R
import com.test.vectortest.base.BaseActivity
import com.test.vectortest.di.activity.ActivityComponent
import com.test.vectortest.ui.adapter.UserAdapter
import com.test.vectortest.ui.data.ResourceState
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainViewModule: MainViewModel

    private val userList = mutableListOf<User>()
    private val adapter = UserAdapter(userList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModule = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        setupRecycler()
        setupViewListener()

        savedInstanceState?.run {
            mainViewModule.restore(getInt(LAST_USER_ID_LOADED), getInt(FIRST_USER_VISIBLE))
        } ?: mainViewModule.loadUsers()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (userList.isNotEmpty()) {
            outState.putInt(LAST_USER_ID_LOADED, userList.last().id)
            outState.putInt(FIRST_USER_VISIBLE, (user_list.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition())
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    private fun setupRecycler() {
        user_list.adapter = adapter
        user_list.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    (layoutManager as LinearLayoutManager).run {
                        mainViewModule.listScrolled(childCount, findFirstVisibleItemPosition(), itemCount)
                    }
                }
            })
        }
    }

    private fun setupViewListener() {
        mainViewModule.positionToScroll.observe(this, Observer { itemToScroll ->
            itemToScroll?.run {
                user_list.scrollToPosition(itemToScroll)
            }
        })

        mainViewModule.usersLiveData.observe(this, Observer { resource ->
            resource?.run {
                handleDataState(status, data, message)
            }
        })
    }

    private fun handleDataState(resourceState: ResourceState, data: List<User>?, message: String?) {
        managementResourceState(resourceState, message)
        if (resourceState == ResourceState.SUCCESS) {
            userList.addAll(data!!)
            adapter.notifyDataSetChanged()
        }
    }

    override fun checkAgain() = {
        mainViewModule.loadUsers()
    }

    override fun tryAgain() = {
        mainViewModule.loadUsers()
    }

    companion object {
        private const val LAST_USER_ID_LOADED: String = "last_user_id_loaded"
        private const val FIRST_USER_VISIBLE: String = "first_user_visible"
    }
}
