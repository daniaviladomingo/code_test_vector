package com.test.data.network

import com.test.data.network.model.mapper.NetworkMapper
import com.test.data.network.retrofit.ApiService
import com.test.domain.model.User
import io.reactivex.Single

class NetworkDataSourceImp(private val apiService: ApiService) : INetworkDataSource {
    override fun getUsers(since: Int): Single<List<User>> = Single.create {
        apiService.getUsers(since).subscribe { apiUsers ->
            it.onSuccess(NetworkMapper.map(apiUsers))
        }
    }
}