package com.test.data.network.retrofit

import com.test.data.network.model.UserApi
import com.test.data.network.model.UserExtraApi
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.github.com/"

interface ApiService {
    @GET("users")
    fun getUsers(@Query("since") since: Int = 0): Single<List<UserApi>>

    @GET("users/{name}")
    fun getDetailUser(@Path("name") userName: String): Single<UserExtraApi>
}