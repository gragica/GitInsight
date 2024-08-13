package com.gragica.gitinsight.data.network.dto

import com.gragica.gitinsight.core.model.RepoCommit
import com.gragica.gitinsight.core.model.RepoTag
import kotlinx.serialization.Serializable

@Serializable
data class RepoTagDto(
    val name: String,
    val commit: RepoCommitDto
) : Dto<RepoTag> {
    override fun toCoreModel(): RepoTag = RepoTag(name, RepoCommit(commit.sha))
}

@Serializable
data class RepoCommitDto(
    val sha: String
)
