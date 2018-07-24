package com.test.data.network

import com.test.data.network.model.UserApi
import io.reactivex.Single
import retrofit2.http.GET

const val BASE_URL = "https://api.github.com/"

interface INetwork {
    @GET
    fun getUsers(since: Int = 0): Single<List<UserApi>>
}