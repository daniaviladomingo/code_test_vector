package com.test.data.network

import com.test.data.network.model.UserApi
import io.reactivex.Single

class NetworkImp : INetwork {
    override fun getUsers(since: Int): Single<List<UserApi>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}