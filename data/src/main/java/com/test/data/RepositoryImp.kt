package com.test.data

import com.test.data.cache.ICacheDataSource
import com.test.data.network.INetworkDataSource
import com.test.domain.model.User
import com.test.domain.repository.IRepository
import io.reactivex.Single

class RepositoryImp(private val cacheDataSource: ICacheDataSource,
                    private val networkDataSource: INetworkDataSource) : IRepository {

    override fun getUsers(since: Int): Single<List<User>> = networkDataSource.getUsers(since).doAfterSuccess { cacheDataSource.save(it) }

    override fun getCachedUsersUntil(idUser: Int): Single<List<User>> = cacheDataSource.getUnitl(idUser)
}