package com.test.data.cache

import com.test.domain.model.User
import io.reactivex.Single

interface ICacheDataSource {
    fun save(users: List<User>)
    fun getUnitl(userId: Int): Single<List<User>>
}