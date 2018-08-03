package com.test.data.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.test.data.cache.model.UserDb
import com.test.data.cache.model.UserDb.Companion.TABLE_NAME
import io.reactivex.Single

@Dao
abstract class UserDao: BaseDao<UserDb> {
    @Query("SELECT * FROM $TABLE_NAME WHERE id <= :userId")
    abstract fun getUsersUntil(userId: Int): Single<List<UserDb>>
}