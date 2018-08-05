package com.test.vectortest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.test.domain.model.User
import com.test.vectortest.R
import com.test.vectortest.base.BaseActivity
import com.test.vectortest.di.activity.ActivityComponent
import com.test.vectortest.ui.adapter.UserAdapter
import com.test.vectortest.ui.data.ResourceState
import com.test.vectortest.utils.log
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
        setContentView(R.layout.activity_main)

        mainViewModule = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        setupRecycler()
        setupViewListener()

        savedInstanceState?.run {
            mainViewModule.restore(getInt(LAST_USER_ID_LOADED), getInt(LAST_USER_VISIBLE))
        } ?: mainViewModule.init()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (userList.isNotEmpty()) {
            "Last user id loaded: ${userList.last().id}".log("ccc")
            "Last user visible: ${(user_list.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()}".log("ccc")
            outState.putInt(LAST_USER_ID_LOADED, userList.last().id)
            outState.putInt(LAST_USER_VISIBLE, (user_list.layoutManager as LinearLayoutManager).findLastVisibleItemPosition())
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
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
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
        when (resourceState) {
            ResourceState.LOADING -> {
                showProgress(getString(R.string.loading_message))
            }
            ResourceState.SUCCESS -> {
                dismissProgress()
                userList.addAll(data!!)
            }
            ResourceState.EMPTY -> {
                dismissProgress()
                Toast.makeText(this, "empty", Toast.LENGTH_LONG).show()
            }
            ResourceState.ERROR -> {
                dismissProgress()
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        private const val LAST_USER_ID_LOADED: String = "last_user_id_loaded"
        private const val LAST_USER_VISIBLE: String = "first_user_visible"
    }
}
