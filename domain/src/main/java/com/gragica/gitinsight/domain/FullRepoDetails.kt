package com.gragica.gitinsight.domain

import com.gragica.gitinsight.core.model.RepoTag

data class FullRepoDetails (
    val repoName: String,
    val ownerName: String,
    val ownerImageUrl: String,
    val forks: Int,
    val watchers: Int,
    val tags: List<RepoTag>
)
