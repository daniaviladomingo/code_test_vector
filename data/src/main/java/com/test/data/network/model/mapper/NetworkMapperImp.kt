package com.test.data.network.model.mapper

import com.test.data.network.model.UserApi
import com.test.data.network.model.UserExtraApi
import com.test.domain.model.User
import com.test.domain.model.UserExtra

class NetworkMapperImp : INetworkMapper {
    override fun mapUser(userApi: UserApi): User = userApi.run { User(login, id, nodeId, avatarUrl, gravatarId, url, htmlUrl, followersUrl, followingUrl, gistsUrl, starredUrl, subscriptionsUrl, organizationsUrl, reposUrl, eventsUrl, receivedEventsUrl, type, siteAdmin) }
    override fun mapUser(listUserApi: List<UserApi>): List<User> = listUserApi.map { mapUser(it) }
    override fun mapUserExtra(userExtraApi: UserExtraApi): UserExtra = userExtraApi.run { UserExtra(name, company, blog, location, email, hireable, bio, publicRepos, publicGists, followers, following, createdAt, updatedAt) }
}