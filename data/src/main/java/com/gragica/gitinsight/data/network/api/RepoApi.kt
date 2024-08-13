package com.gragica.gitinsight.data.network.api

import com.gragica.gitinsight.data.network.dto.RepoTagDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoApi {

    @GET("/repos/{userId}/{repoId}/tags")
    suspend fun getRepoTags(
        @Path("userId") userId: String,
        @Path("repoId") repoId: String
    ): Response<List<RepoTagDto>>
}
