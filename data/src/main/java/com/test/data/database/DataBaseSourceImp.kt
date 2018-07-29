package com.test.data.database

import com.test.data.database.definition.AppDatabase
import com.test.data.database.model.UserDb
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import io.reactivex.Single

class DataBaseSourceImp(private val appDatabase: AppDatabase, private val mapper: Mapper<UserDb, User>) : IDataBaseSource {
    override fun save(users: List<User>) {
        users.forEach { appDatabase.userDao().create(mapper.inverseMap(it)) }
    }

    override fun getUnitl(userId: Int): Single<List<User>> = Single.create {
        it.onSuccess(mapper.map(appDatabase.userDao().getUsersUntil(userId)))
    }
}