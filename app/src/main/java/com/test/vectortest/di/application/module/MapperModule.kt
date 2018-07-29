package com.test.vectortest.di.application.module

import com.test.data.database.model.UserDb
import com.test.data.database.model.mapper.DataBaseMapper
import com.test.data.network.model.UserApi
import com.test.data.network.model.mapper.NetworkMapper
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Provides
    @Singleton
    fun provideNetworkMapper(): Mapper<UserApi, User> = NetworkMapper()

    @Provides
    @Singleton
    fun provideDataBaseMapper(): Mapper<UserDb, User> = DataBaseMapper()
}