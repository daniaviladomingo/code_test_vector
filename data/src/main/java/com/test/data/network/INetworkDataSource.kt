package com.test.data.network

import com.test.domain.model.User
import com.test.domain.model.UserExtra
import io.reactivex.Single

interface INetworkDataSource {
    fun getUsers(since: Int): Single<List<User>>
    fun getUserExtra(userName: String): Single<UserExtra>
}