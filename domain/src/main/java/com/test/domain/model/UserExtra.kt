package com.test.domain.model

data class UserExtra(
        val name: String,
        val company: String,
        val blog: String,
        val location: String,
        val email: String?,
        val hireable: String?,
        val bio: String?,
        val publicRepos: Int,
        val publiGists: Int,
        val followers: Int,
        val following: Int,
        val createdAt: String,
        val updatedAt: String
)