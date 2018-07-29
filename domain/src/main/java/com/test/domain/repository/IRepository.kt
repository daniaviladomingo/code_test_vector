package com.test.domain.repository

import com.test.domain.model.User
import io.reactivex.Single

interface IRepository {
    fun getUsers(since: Int): Single<List<User>>
}