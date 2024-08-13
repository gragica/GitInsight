package com.gragica.gitinsight.domain

import com.gragica.gitinsight.data.repository.repo.RepoRepository
import com.gragica.gitinsight.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRepoDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val repoRepository: RepoRepository
) {
    suspend operator fun invoke(userId: String, repoId: String): Flow<Result<FullRepoDetails>> =
        flow {
            val userFlow = userRepository.getUser(userId)
            val repoFlow = userRepository.getUserRepos(userId)
            val repoTagsFlow = repoRepository.getRepoTags(userId, repoId)

            combine(userFlow, repoFlow, repoTagsFlow) { userResult, repoResult, tagsResult ->
                if (userResult.isSuccess && repoResult.isSuccess && tagsResult.isSuccess) {

                    val userValue = userResult.getOrNull()
                    val repoValue = repoResult.getOrNull()?.first { it.name == repoId }
                    val repoTagsValue = tagsResult.getOrNull()

                    if (repoValue != null && repoTagsValue != null && userValue != null)
                        emit(
                            Result.success(
                                FullRepoDetails(
                                    repoValue.name,
                                    userValue.name,
                                    userValue.imageUrl,
                                    repoValue.forksCount,
                                    repoValue.watchCount,
                                    repoTagsValue
                                )
                            )
                        )
                    else
                        emit(Result.failure(Throwable("SOMETHING IS NULL")))
                } else {
                    emit(Result.failure(Throwable("API failure")))
                }
            }.collect {}
        }
}
