package com.gragica.gitinsight

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.gragica.gitinsight.ui.screens.repo.RepoScreen
import com.gragica.gitinsight.ui.screens.user.UserScreen
import kotlinx.serialization.Serializable

@Serializable
data class UserScreenId(
    val userId: String
)

@Serializable
data class RepoScreenId(
    val userId: String,
    val repoId: String
)

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = UserScreenId("octocat")) {
        composable<UserScreenId> {
            val args = it.toRoute<UserScreenId>()
            UserScreen(
                userId = args.userId,
                onRepoSelected = { userId, repoId ->
                    navController.navigate(
                        RepoScreenId(
                            userId,
                            repoId
                        )
                    )
                }
            )
        }

        composable<RepoScreenId> {
            val args = it.toRoute<RepoScreenId>()
            RepoScreen(args.userId, args.repoId, {
                navController.navigateUp()
            })
        }
    }
}
