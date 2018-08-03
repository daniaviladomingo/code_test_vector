package com.test.data

import com.test.data.database.IDataBaseSource
import com.test.data.network.INetworkDataSource
import com.test.domain.model.User
import com.test.domain.repository.IRepository
import io.reactivex.Single

class RepositoryImp(private val dataBaseSource: IDataBaseSource,
                    private val networkDataSource: INetworkDataSource) : IRepository {

    override fun getUsers(since: Int): Single<List<User>> = networkDataSource.getUsers(since).doAfterSuccess { dataBaseSource.save(it) }

    override fun getCachedUsersUntil(idUser: Int): Single<List<User>> = dataBaseSource.getUnitl(idUser)
}