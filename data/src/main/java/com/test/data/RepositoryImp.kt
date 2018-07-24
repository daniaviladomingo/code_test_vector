package com.test.data

import com.test.domain.model.User
import com.test.domain.repository.IRepository
import io.reactivex.Single

class RepositoryImp : IRepository {
    override fun getUsers(since: Int): Single<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}