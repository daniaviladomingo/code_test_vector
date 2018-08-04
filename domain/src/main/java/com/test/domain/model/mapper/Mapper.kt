package com.test.domain.model.mapper

abstract class Mapper<M, P> {
    abstract fun map(model: M): P
    abstract fun inverseMap(model: P): M

    fun map(values: List<M>) = values.forEach { map(it) }
    fun inverseMap(values: List<P>) = values.forEach { inverseMap(it) }
}