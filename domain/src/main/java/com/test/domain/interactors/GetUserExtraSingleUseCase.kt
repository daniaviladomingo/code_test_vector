package com.test.domain.interactors

import com.test.domain.interactors.type.SingleUseCaseWithParameter
import com.test.domain.model.UserExtra
import com.test.domain.repository.IRepository
import io.reactivex.Single

class GetUserExtraSingleUseCase (private val repository: IRepository) : SingleUseCaseWithParameter<String, UserExtra> {
    override fun execute(parameter: String): Single<UserExtra> = repository.getUserExtra(parameter)
}