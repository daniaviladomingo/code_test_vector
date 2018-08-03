package com.test.vectortest.di.application.module

import com.test.data.RepositoryImp
import com.test.data.cache.ICacheDataSource
import com.test.data.network.INetworkDataSource
import com.test.domain.repository.IRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Provides
    @Singleton
    fun provideRepository(cacheDataSource: ICacheDataSource, networkDataSource: INetworkDataSource): IRepository = RepositoryImp(cacheDataSource, networkDataSource)
}