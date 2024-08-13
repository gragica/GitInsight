package com.gragica.gitinsight.data.repository

import com.gragica.gitinsight.core.model.Repo
import com.gragica.gitinsight.core.model.User
import com.gragica.gitinsight.data.network.api.UserApi
import com.gragica.gitinsight.data.repository.util.apiCallToListFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    private var userMap: HashMap<String, User> = hashMapOf()

    private var repoMap: HashMap<String, List<Repo>> = hashMapOf()

    override suspend fun getUser(userId: String): Flow<Result<User>> = flow {
        val cachedUser = userMap[userId]
        if (cachedUser == null) {
            try {
                val response = userApi.getUser(userId)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val coreModel = body.toCoreModel()
                        userMap[userId] = coreModel
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
        } else emit(Result.success(cachedUser))
    }

    override suspend fun getUserRepos(userId: String): Flow<Result<List<Repo>>> =
        flow {
            val cachedRepos = repoMap[userId]
            if (cachedRepos == null) {
                try {
                    val response = userApi.getUserRepos(userId)
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            val coreModels = body.map { it.toCoreModel() }
                            repoMap[userId] = coreModels
                            emit(Result.success(coreModels))
                        } else {
                            emit(Result.failure(NullPointerException("Response body was null")))
                        }
                    } else {
                        emit(Result.failure(HttpException(response)))
                    }
                } catch (e: Exception) {
                    emit(Result.failure(Exception("Something went wrong", e)))
                }
            } else emit(Result.success(cachedRepos))
        }
}
