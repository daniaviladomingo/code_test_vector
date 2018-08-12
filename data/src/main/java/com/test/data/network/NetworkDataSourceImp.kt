package com.test.data.network


import com.test.data.network.model.mapper.INetworkMapper
import com.test.data.network.retrofit.ApiService
import com.test.domain.model.User
import com.test.domain.model.UserExtra
import io.reactivex.Single

class NetworkDataSourceImp(private val apiService: ApiService, private val mapper: INetworkMapper) : INetworkDataSource {
    override fun getUsers(since: Int): Single<List<User>> = apiService.getUsers(since).map { mapper.mapUser(it) }
    override fun getUserExtra(userName: String): Single<UserExtra> = apiService.getDetailUser(userName).map { mapper.mapUserExtra(it) }
}