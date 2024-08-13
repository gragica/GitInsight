package com.gragica.gitinsight.data.repository

import com.gragica.gitinsight.core.model.RepoTag
import com.gragica.gitinsight.data.network.api.RepoApi
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class DefaultRepoRepository @Inject constructor(
    private val api: RepoApi
) : RepoRepository {

    private var repoTagsMap: HashMap<String, List<RepoTag>> = hashMapOf()

//    override suspend fun getRepo(userId: String, repoId: String) =
//        flow {
//            val cachedRepo = repoDetailsMap[repoId]
//            if (cachedRepo == null) {
//                try {
//                    val response = api.getRepo(userId, repoId)
//                    if (response.isSuccessful) {
//                        val body = response.body()
//                        if (body != null) {
//                            val coreModel = body.toCoreModel()
//                            repoDetailsMap[repoId] = coreModel
//                            emit(Result.success(coreModel))
//                        } else {
//                            emit(Result.failure(NullPointerException("Response body was null")))
//                        }
//                    } else {
//                        emit(Result.failure(HttpException(response)))
//                    }
//                } catch (e: Exception) {
//                    emit(Result.failure(Exception("Something went wrong", e)))
//                }
//            } else emit(Result.success(cachedRepo))
//        }

    override suspend fun getRepoTags(userId: String, repoId: String) =
        flow {
            val cachedRepoTags = repoTagsMap[repoId]
            if (cachedRepoTags == null) {
                try {
                    val response = api.getRepoTags(userId, repoId)
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val coreModel = body.map { it.toCoreModel() }
                            repoTagsMap[repoId] = coreModel
                            emit(Result.success(coreModel))
                        } else {
                            emit(Result.failure(NullPointerException("Response body was null")))
                        }
                    } else {
                        emit(Result.failure(HttpException(response)))
                    }
                } catch (e: Exception) {
                    emit(Result.failure(Exception("Something went wrong", e)))
                }
            } else emit(Result.success(cachedRepoTags))
        }
}
