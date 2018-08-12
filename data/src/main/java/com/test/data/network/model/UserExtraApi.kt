package com.test.data.network.model

import com.google.gson.annotations.SerializedName

data class UserExtraApi(
        @SerializedName("name") val name: String,
        @SerializedName("company") val company: String,
        @SerializedName("blog") val blog: String,
        @SerializedName("location") val location: String,
        @SerializedName("email") val email: String?,
        @SerializedName("hireable") val hireable: String?,
        @SerializedName("bio") val bio: String?,
        @SerializedName("public_repos") val publicRepos: Int,
        @SerializedName("public_gists") val publicGists: Int,
        @SerializedName("followers") val followers: Int,
        @SerializedName("following") val following: Int,
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("updated_at") val updatedAt: String
)