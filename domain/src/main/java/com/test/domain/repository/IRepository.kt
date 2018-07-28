package com.test.domain.repository

import com.test.domain.model.User
import io.reactivex.Single

interface IRepository {
    fun getUsers(): Single<List<User>>
}