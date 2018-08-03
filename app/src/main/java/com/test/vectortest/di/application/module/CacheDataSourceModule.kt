package com.test.vectortest.di.application.module

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.test.data.cache.CacheDataSourceImp
import com.test.data.cache.ICacheDataSource
import com.test.data.cache.definition.AppDatabase
import com.test.data.cache.definition.AppDatabase.Companion.DATABASE_NAME
import com.test.data.cache.model.UserDb
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import com.test.vectortest.di.application.DataBaseName
import com.test.vectortest.di.application.ForApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataSourceModule {
    @Provides
    @Singleton
    fun provideDataBaseSource(roomDatabase: RoomDatabase, mapper: Mapper<UserDb, User>): ICacheDataSource = CacheDataSourceImp(roomDatabase as AppDatabase, mapper)

    @Provides
    @Singleton
    fun provideRoomDataBase(@ForApplication context: Context, @DataBaseName dataBaseName: String): RoomDatabase = Room.databaseBuilder(context, AppDatabase::class.java, dataBaseName).allowMainThreadQueries().build()

    @Provides
    @Singleton
    @DataBaseName
    fun provideDataBaseName(): String = DATABASE_NAME
}