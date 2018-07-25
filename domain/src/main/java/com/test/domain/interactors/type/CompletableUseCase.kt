package com.test.domain.interactors.type

import io.reactivex.Completable

interface CompletableUseCase {
    fun execute(): Completable
}