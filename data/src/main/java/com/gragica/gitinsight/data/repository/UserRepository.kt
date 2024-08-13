package com.gragica.gitinsight.data.repository

import com.gragica.gitinsight.core.model.Repo
import com.gragica.gitinsight.core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUser(userId: String): Flow<Result<User>>

    suspend fun getUserRepos(userId: String): Flow<Result<List<Repo>>>
}
