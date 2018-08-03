package com.test.data.cache.model.mapper

import com.test.data.cache.model.UserDb
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper

class DataBaseMapper : Mapper<UserDb, User>() {
    override fun map(model: UserDb): User = model.run {
        User(login, id, nodeId, avatarUrl, gravatarId, url, htmlUrl, followersUrl, followingUrl, gistsUrl, starredUrl, subscriptionsUrl, organizationsUrl, reposUrl, eventsUrl, receivedEventsUrl, type, siteAdmin)
    }

    override fun inverseMap(model: User): UserDb = model.run {
        UserDb(id, login, nodeId, avatarUrl, gravatarId, url, htmlUrl, followersUrl, followingUrl, gistsUrl, starredUrl, subscriptionsUrl, organizationsUrl, reposUrl, eventsUrl, receivedEventsUrl, type, siteAdmin)
    }
}