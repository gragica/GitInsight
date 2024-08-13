package com.gragica.gitinsight.data.network.api

import com.gragica.gitinsight.data.network.dto.RepoDto
import com.gragica.gitinsight.data.network.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: String): Response<UserDto>

    @GET("/users/{userId}/repos")
    suspend fun getUserRepos(@Path("userId") userId: String): Response<List<RepoDto>>
}
