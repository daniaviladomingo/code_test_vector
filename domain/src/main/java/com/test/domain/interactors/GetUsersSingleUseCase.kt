package com.test.domain.interactors

import com.test.domain.interactors.type.SingleUseCaseWithParameter
import com.test.domain.model.User
import com.test.domain.repository.IRepository
import io.reactivex.Single

class GetUsersSingleUseCase(private val repository: IRepository): SingleUseCaseWithParameter<Int, List<User>> {
    override fun execute(parameter: Int): Single<List<User>> = repository.getUsers(parameter)
}