package com.test.data.database.model.mapper

import com.test.data.database.model.UserDb
import com.test.domain.model.User
import com.test.domain.model.mapper.Mapper

class DataBaseMapper: Mapper<UserDb, User>() {
    override fun map(model: UserDb): User = model.run {
        User(login, id, nodeId, avatarUrl, gravatarId, url, htmlUrl, followersUrl, followingUrl, gistsUrl, starredUrl, subscriptionsUrl, organizationsUrl, reposUrl, eventsUrl, receivedEventsUrl, type, siteAdmin)
    }

    override fun inverseMap(model: User): UserDb = UserDb().apply {
        login = model.login
        id = model.id
        nodeId = model.nodeId
        avatarUrl = model.avatarUrl
        gravatarId = model.gravatarId
        url = model.url
        htmlUrl = model.htmlUrl
        followersUrl = model.followersUrl
        followingUrl = model.followingUrl
        gistsUrl = model.gistsUrl
        starredUrl = model.starredUrl
        subscriptionsUrl = model.subscriptionsUrl
        organizationsUrl = model.organizationsUrl
        reposUrl = model.reposUrl
        eventsUrl = model.eventsUrl
        receivedEventsUrl = model.receivedEventsUrl
        type = model.type
        siteAdmin = model.siteAdmin
    }
}