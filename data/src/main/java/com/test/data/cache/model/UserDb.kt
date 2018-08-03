package com.test.data.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.test.data.cache.model.UserDb.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME,
        indices = [(Index("id"))])

data class UserDb(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "login")
        val login: String,
        @ColumnInfo(name = "node_id")
        val nodeId: String,
        @ColumnInfo(name = "avatar_url")
        val avatarUrl: String,
        @ColumnInfo(name = "gravatar_id")
        val gravatarId: String,
        @ColumnInfo(name = "url")
        val url: String,
        @ColumnInfo(name = "html_url")
        val htmlUrl: String,
        @ColumnInfo(name = "followers_url")
        val followersUrl: String,
        @ColumnInfo(name = "following_url")
        val followingUrl: String,
        @ColumnInfo(name = "gists_url")
        val gistsUrl: String,
        @ColumnInfo(name = "starred_url")
        val starredUrl: String,
        @ColumnInfo(name = "subscriptions_url")
        val subscriptionsUrl: String,
        @ColumnInfo(name = "organizations_url")
        val organizationsUrl: String,
        @ColumnInfo(name = "repos_url")
        val reposUrl: String,
        @ColumnInfo(name = "events_url")
        val eventsUrl: String,
        @ColumnInfo(name = "received_events_url")
        val receivedEventsUrl: String,
        @ColumnInfo(name = "type")
        val type: String,
        @ColumnInfo(name = "site_admin")
        val siteAdmin: Boolean) {

    companion object {
        const val TABLE_NAME = "users"
    }
}