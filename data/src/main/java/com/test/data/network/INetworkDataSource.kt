package com.test.data.network

import com.test.domain.model.User
import io.reactivex.Single

interface INetworkDataSource {
    fun getUsers(): Single<List<User>>
}