package com.test.data.cache.definition

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.test.data.cache.dao.UserDao
import com.test.data.cache.definition.AppDatabase.Companion.DATABASE_VERSION
import com.test.data.cache.model.UserDb

@Database(
        entities = [UserDb::class],
        version = DATABASE_VERSION)

abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "fever.db"
        const val DATABASE_VERSION = 1
    }
    abstract fun userDao(): UserDao
}