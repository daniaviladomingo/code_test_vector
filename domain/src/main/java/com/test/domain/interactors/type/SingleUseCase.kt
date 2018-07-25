package com.test.domain.interactors.type

import io.reactivex.Single

interface SingleUseCase<T> {
    fun execute(): Single<T>
}