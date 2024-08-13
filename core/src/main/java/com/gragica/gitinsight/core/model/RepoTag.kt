package com.gragica.gitinsight.core.model

data class RepoTag(
    val name: String,
    val commit: RepoCommit
)

data class RepoCommit(
    val sha: String
)
