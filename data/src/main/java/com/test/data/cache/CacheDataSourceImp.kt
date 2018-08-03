package com.test.data.cache

import com.test.data.cache.definition.AppDatabase
import com.test.data.cache.model.UserDb
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import io.reactivex.Single

class CacheDataSourceImp(private val appDatabase: AppDatabase, private val mapper: Mapper<UserDb, User>) : ICacheDataSource {
    override fun save(users: List<User>) {
        users.forEach { appDatabase.userDao().create(mapper.inverseMap(it)) }
    }

    override fun getUntil(idUser: Int): Single<List<User>> = appDatabase.userDao().getUsersUntil(idUser).map { mapper.map(it) }
}