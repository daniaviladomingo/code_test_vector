package com.test.domain.repository

import com.test.domain.model.User
import com.test.domain.model.UserExtra
import io.reactivex.Single

interface IRepository {
    fun getUsers(since: Int): Single<List<User>>
    fun getCachedUsersUntil(idUser: Int): Single<List<User>>
    fun getUserExtra(userName: String): Single<UserExtra>
}