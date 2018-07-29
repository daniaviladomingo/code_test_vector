package com.test.data.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.test.data.database.model.UserDb.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME,
        indices = [(Index("id"))])

class UserDb {
    companion object {
        const val TABLE_NAME = "users"
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
    @ColumnInfo(name = "login")
    var login: String = ""
    @ColumnInfo(name = "node_id")
    var nodeId: String = ""
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String = ""
    @ColumnInfo(name = "gravatar_id")
    var gravatarId: String = ""
    @ColumnInfo(name = "url")
    var url: String = ""
    @ColumnInfo(name = "html_url")
    var htmlUrl: String = ""
    @ColumnInfo(name = "followers_url")
    var followersUrl: String = ""
    @ColumnInfo(name = "following_url")
    var followingUrl: String = ""
    @ColumnInfo(name = "gists_url")
    var gistsUrl: String = ""
    @ColumnInfo(name = "starred_url")
    var starredUrl: String = ""
    @ColumnInfo(name = "subscriptions_url")
    var subscriptionsUrl: String = ""
    @ColumnInfo(name = "organizations_url")
    var organizationsUrl: String = ""
    @ColumnInfo(name = "repos_url")
    var reposUrl: String = ""
    @ColumnInfo(name = "events_url")
    var eventsUrl: String = ""
    @ColumnInfo(name = "received_events_url")
    var receivedEventsUrl: String = ""
    @ColumnInfo(name = "type")
    var type: String = ""
    @ColumnInfo(name = "site_admin")
    var siteAdmin: Boolean = false
}