package com.test.data.network

import com.test.data.network.model.UserApi
import com.test.data.network.retrofit.ApiService
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import io.reactivex.Single

class NetworkDataSourceImp(private val apiService: ApiService, private val networkMapper: Mapper<UserApi, User>) : INetworkDataSource {

    private var since = 0

    override fun getUsers(): Single<List<User>> = Single.create {
        apiService.getUsers(since).subscribe({ apiUsers ->
            since = apiUsers.last().id
            it.onSuccess(networkMapper.map(apiUsers))
        }) { error ->
            it.onError(error)
        }
    }
}