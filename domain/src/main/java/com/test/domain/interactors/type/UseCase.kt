package com.test.domain.interactors.type

import io.reactivex.Observable

interface UseCase<T> {
    fun execute(): Observable<T>
}