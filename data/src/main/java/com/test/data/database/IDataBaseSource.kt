package com.test.data.database

import com.test.domain.model.User
import io.reactivex.Single

interface IDataBaseSource {
    fun save(users: List<User>)
    fun getUnitl(userId: Int): Single<List<User>>
}