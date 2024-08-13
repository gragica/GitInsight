package com.gragica.gitinsight.core.model

data class Repo(
    val name: String,
    val forksCount: Int,
    val openIssues: Int,
    val watchCount: Int,
    val tags: List<RepoTag>? = null
)