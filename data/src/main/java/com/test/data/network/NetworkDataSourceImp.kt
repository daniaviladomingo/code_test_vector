package com.test.data.network

import com.test.data.network.model.UserApi
import com.test.data.network.retrofit.ApiService
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NetworkDataSourceImp(private val apiService: ApiService, private val mapper: Mapper<UserApi, User>) : INetworkDataSource {
    override fun getUsers(since: Int): Single<List<User>> = Single.create {
        apiService.getUsers(since)
                .subscribeOn(Schedulers.newThread())
                .subscribe({ apiUsers ->
                    it.onSuccess(mapper.map(apiUsers))
                }) { error ->
                    it.onError(error)
                }
    }
}