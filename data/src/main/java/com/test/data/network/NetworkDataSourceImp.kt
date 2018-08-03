package com.test.data.network

import com.test.data.network.model.UserApi
import com.test.data.network.retrofit.ApiService
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import io.reactivex.Single

class NetworkDataSourceImp(private val apiService: ApiService, private val mapper: Mapper<UserApi, User>) : INetworkDataSource {
    override fun getUsers(since: Int): Single<List<User>> = apiService.getUsers(since).map { mapper.map(it) }
}