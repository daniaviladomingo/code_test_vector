package com.test.vectortest.ui

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.data.RepositoryImp
import com.test.data.database.DataBaseSourceImp
import com.test.data.database.definition.AppDatabase
import com.test.data.database.model.mapper.DataBaseMapper
import com.test.data.network.NetworkDataSourceImp
import com.test.data.network.model.mapper.NetworkMapper
import com.test.data.network.retrofit.ApiService
import com.test.domain.model.User
import com.test.vectortest.R
import com.test.vectortest.base.BaseActivity
import com.test.vectortest.base.ScopePresenter
import com.test.vectortest.di.activity.ActivityComponent
import com.test.vectortest.ui.adapter.UserAdapter
import com.test.vectortest.utils.log
import com.test.vectortest.utils.schedulers.ScheduleProviderImp
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.IView {

    @Inject
    lateinit var presenter: MainContract.IPresenter

    private val userList = mutableListOf<User>()
    private val adapter = UserAdapter(userList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        RepositoryImp( DataBaseSourceImp(Room.databaseBuilder(this, AppDatabase::class.java, "foriro").allowMainThreadQueries().build() as AppDatabase, DataBaseMapper()),
//
//        NetworkDataSourceImp(Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .client(OkHttpClient().newBuilder().addInterceptor { chain ->
//                    chain.run {
//                        request()
//                        val builder = request().newBuilder().header("Authorization", Credentials.basic("daniaviladomingo@gmail.com", "ana1636ana"))
//                        proceed(builder.build())
//                    }
//                }.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build()
//                .create(ApiService::class.java), NetworkMapper())
//        )
//
//
//                .getUsers(0)
//                .subscribeOn(ScheduleProviderImp().io())
//                .subscribe { it ->
//                    it.size.log()
//                }
//
        user_list.adapter = adapter

        setupScrollListener()

        savedInstanceState?.run {
            presenter.restore(getInt(LAST_USER_ID_LOADED), getInt(LAST_USER_VISIBLE))
        } ?: presenter.init()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (userList.isNotEmpty()) {
            outState.putInt(LAST_USER_ID_LOADED, userList.last().id)
            outState.putInt(LAST_USER_VISIBLE, (user_list.layoutManager as LinearLayoutManager).findLastVisibleItemPosition())
        }
    }

    override fun getScopePresenter(): ScopePresenter = presenter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun addUsers(users: List<User>) {
        userList.addAll(users)
        runOnUiThread {
            adapter.notifyDataSetChanged()
        }
    }

    override fun scrollListToItem(scrollToItem: Int) {
        runOnUiThread {
            user_list.scrollToPosition(scrollToItem)
        }
    }

    override fun showProgressLoading() {
        showProgress(getString(R.string.loading_message))
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

    companion object {
        private const val LAST_USER_ID_LOADED: String = "last_user_id_loaded"
        private const val LAST_USER_VISIBLE: String = "first_user_visible"
    }
}
