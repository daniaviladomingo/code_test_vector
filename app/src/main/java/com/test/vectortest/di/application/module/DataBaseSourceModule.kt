package com.test.vectortest.di.application.module

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.test.data.database.DataBaseSourceImp
import com.test.data.database.IDataBaseSource
import com.test.data.database.definition.AppDatabase
import com.test.data.database.definition.AppDatabase.Companion.DATABASE_NAME
import com.test.data.database.model.UserDb
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import com.test.vectortest.di.application.DataBaseName
import com.test.vectortest.di.application.Password
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseSourceModule {
    @Provides
    @Singleton
    fun provideDataBaseSource(roomDatabase: RoomDatabase, mapper: Mapper<UserDb, User>): IDataBaseSource = DataBaseSourceImp(roomDatabase as AppDatabase, mapper)

    @Provides
    @Singleton
    fun provideRoomDataBase(context: Context, @DataBaseName dataBaseName: String): RoomDatabase = Room.databaseBuilder(context, AppDatabase::class.java, dataBaseName).allowMainThreadQueries().build()

    @Provides
    @Singleton
    @Password
    fun provideDataBaseName(): String = DATABASE_NAME
}