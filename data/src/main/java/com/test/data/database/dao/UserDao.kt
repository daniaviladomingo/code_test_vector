package com.test.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.test.data.database.model.UserDb
import com.test.data.database.model.UserDb.Companion.TABLE_NAME

@Dao
abstract class UserDao: BaseDao<UserDb> {
    @Query("SELECT * FROM $TABLE_NAME WHERE id <= :userId")
    abstract fun getUsersUntil(userId: Int): List<UserDb>
}