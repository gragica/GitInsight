package com.gragica.gitinsight.data.repository.util

import com.gragica.gitinsight.data.network.dto.Dto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T : Dto<R>, R> apiCallToFlow(
    apiCall: suspend () -> Response<T>,
    additionalAction: (R) -> Unit = {}
): Flow<Result<R>> = flow {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(Result.success(body.toCoreModel()))
            } else {
                emit(Result.failure(NullPointerException("Response body was null")))
            }
        } else {
            emit(Result.failure(HttpException(response)))
        }
    } catch (e: Exception) {
        emit(Result.failure(Exception("Something went wrong", e)))
    }
}

suspend fun <T : Dto<R>, R> apiCallToListFlow(
    apiCall: suspend () -> Response<List<T>>,
    additionalAction: (List<R>) -> Unit = {}
): Flow<Result<List<R>>> = flow {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val coreModelList = body.map { it.toCoreModel() }
                emit(Result.success(coreModelList))
                additionalAction(coreModelList)
            } else {
                emit(Result.failure(NullPointerException("Response body was null")))
            }
        } else {
            emit(Result.failure(HttpException(response)))
        }
    } catch (e: IOException) {
        emit(Result.failure(IOException("No internet connection", e)))
    } catch (e: HttpException) {
        emit(Result.failure(e))
    } catch (e: Exception) {
        emit(Result.failure(Exception("Something went wrong", e)))
    }
}
