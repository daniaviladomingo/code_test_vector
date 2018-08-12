package com.test.vectortest.di.application.module

import com.test.data.cache.model.UserDb
import com.test.data.cache.model.mapper.DataBaseMapper
import com.test.data.network.model.mapper.INetworkMapper
import com.test.data.network.model.mapper.NetworkMapperImp
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Provides
    @Singleton
    fun provideNetworkMapper(): INetworkMapper = NetworkMapperImp()

    @Provides
    @Singleton
    fun provideDataBaseMapper(): Mapper<UserDb, User> = DataBaseMapper()
}