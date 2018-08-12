package com.test.data.network.model.mapper

import com.test.data.network.model.UserApi
import com.test.data.network.model.UserExtraApi
import com.test.domain.model.User
import com.test.domain.model.UserExtra

interface INetworkMapper {
    fun mapUser(userApi: UserApi): User
    fun mapUser(listUserApi: List<UserApi>): List<User>
    fun mapUserExtra(userExtraApi: UserExtraApi): UserExtra
}