package com.gragica.gitinsight.data.network.dto

import com.google.gson.annotations.SerializedName
import com.gragica.gitinsight.core.model.Repo
import kotlinx.serialization.Serializable

@Serializable
data class RepoDto(
    val name: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("open_issues") val openIssues: Int,
    @SerializedName("watchers") val watchCount: Int,
    val tags: List<RepoTagDto>? = null
) : Dto<Repo> {
    override fun toCoreModel() = Repo(name, forksCount, openIssues, watchCount)
}
