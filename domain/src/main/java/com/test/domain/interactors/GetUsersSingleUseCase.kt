package com.test.domain.interactors

import com.test.domain.interactors.type.SingleUseCase
import com.test.domain.model.User
import com.test.domain.repository.IRepository
import io.reactivex.Single

class GetUsersSingleUseCase(private val repository: IRepository): SingleUseCase<List<User>> {
    override fun execute(): Single<List<User>> = repository.getUsers()
}