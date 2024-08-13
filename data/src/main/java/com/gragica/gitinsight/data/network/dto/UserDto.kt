package com.gragica.gitinsight.data.network.dto

import com.google.gson.annotations.SerializedName
import com.gragica.gitinsight.core.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val name: String,
    @SerializedName("avatar_url") val avatarUrl: String
) : Dto<User> {
    override fun toCoreModel() = User(name, avatarUrl)
}
