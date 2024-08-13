package com.gragica.gitinsight.data

import com.gragica.gitinsight.data.network.api.RepoApi
import com.gragica.gitinsight.data.network.api.UserApi
import com.gragica.gitinsight.data.repository.DefaultRepoRepository
import com.gragica.gitinsight.data.repository.DefaultUserRepository
import com.gragica.gitinsight.data.repository.RepoRepository
import com.gragica.gitinsight.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi): UserRepository = DefaultUserRepository(api)

    @Provides
    @Singleton
    fun provideRepoRepository(api: RepoApi): RepoRepository = DefaultRepoRepository(api)
}
