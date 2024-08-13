package com.gragica.gitinsight.data.repository

import com.gragica.gitinsight.core.model.RepoTag
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun getRepoTags(userId: String, repoId: String): Flow<Result<List<RepoTag>>>
}
