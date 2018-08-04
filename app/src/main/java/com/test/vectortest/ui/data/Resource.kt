package com.test.vectortest.ui.data

class Resource<out T> constructor(val status: ResourceState, val data: T?, val message: String?) {

    fun <T> success(data: T): Resource<T> = Resource(ResourceState.SUCCESS, data, null)

    fun <T> error(message: String, data: T?): Resource<T> = Resource(ResourceState.ERROR, null, message)

    fun <T> loading(): Resource<T> = Resource(ResourceState.LOADING, null, null)
}