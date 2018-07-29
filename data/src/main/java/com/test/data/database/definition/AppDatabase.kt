package com.test.data.database.definition

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.test.data.database.dao.UserDao
import com.test.data.database.definition.AppDatabase.Companion.DATABASE_VERSION
import com.test.data.database.model.UserDb

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