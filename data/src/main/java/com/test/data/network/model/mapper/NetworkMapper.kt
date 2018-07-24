package com.test.data.network.model.mapper

import com.test.data.network.model.UserApi
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper

object NetworkMapper : Mapper<UserApi, User>() {
    override fun map(model: UserApi): User = model.run { User(login, id, nodeId, avatarUrl, gravatarId, url, htmlUrl, followersUrl, followingUrl, gistsUrl, starredUrl, subscriptionsUrl, organizationsUrl, reposUrl, eventsUrl, receivedEventsUrl, type, siteAdmin) }

    override fun inverseMap(model: User): UserApi {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}