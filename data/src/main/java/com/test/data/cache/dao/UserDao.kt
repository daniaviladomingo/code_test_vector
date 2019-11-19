package com.test.data.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.test.data.cache.model.UserDb
import com.test.data.cache.model.UserDb.Companion.TABLE_NAME
import io.reactivex.Single

@Dao
abstract class UserDao: BaseDao<UserDb> {
    @Query("SELECT * FROM $TABLE_NAME WHERE id <= :idUser")
    abstract fun getUsersUntil(idUser: Int): Single<List<UserDb>>
}