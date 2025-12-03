package com.bytedance.croissantapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bytedance.croissantapp.domain.model.Post
import com.bytedance.croissantapp.presentation.detail.DetailScreen
import com.bytedance.croissantapp.presentation.hashtag.HashtagScreen
import com.bytedance.croissantapp.presentation.home.HomeScreen
import com.bytedance.croissantapp.presentation.profile.ProfileScreen

/**
 * 导航路由常量
 */
object Routes {
    const val HOME = "home"
    const val PROFILE = "profile"
    const val DETAIL = "detail/{postId}" // 详情页，带参数

    const val HASHTAG ="hashtag/{hashtag}"


    // 生成详情页路由的辅助函数
    fun detail(postId: String) = "detail/$postId"
    // 话题页路由
    fun hashtag(hashtag: String) = "hashtag/$hashtag"
}

/**
 * 应用的导航图
 *
 * @param navController 导航控制器
 * @param startDestination 起始目的地
 */
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Routes.HOME,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        // 首页
        composable(route = Routes.HOME) {
            backStackEntry ->
            // 监听从详情页返回的结果
            val shouldRefresh = backStackEntry
                .savedStateHandle
                .getStateFlow("refresh_from_detail", false)

            HomeScreen(
                onNavigateToDetail = { post ->
                    // 跳转到详情页
                    navController.navigate(Routes.detail(post.postId))

                    // Post 对象存入详情页的 SavedStateHandle
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("selected_post", post)
                },
                shouldRefresh = shouldRefresh
            )
        }

        // 个人主页
        composable(route = Routes.PROFILE) {
            ProfileScreen()
        }

        // 详情页
        composable(route = Routes.DETAIL) { backStackEntry ->
            // 获取路由参数
            val postId = backStackEntry.arguments?.getString("postId") ?: ""

            // 从当前页的 SavedStateHandle 获取传递的 Post 对象
            val post = backStackEntry.savedStateHandle.get<Post>("selected_post")

            DetailScreen(
                postId = postId,
                initialPost = post,  // 传递 Post 对象
                onNavigateBack = {
                    // 返回前设置刷新信号（在动画开始前立即触发）
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("refresh_from_detail", true)

                    navController.navigateUp()
                },
                onHashtagClick = { hashtag ->
                    val encoded = java.net.URLEncoder.encode(hashtag, "UTF-8")
                    navController.navigate(Routes.hashtag(encoded))
                }

            )
        }

        composable(route = Routes.HASHTAG) { backStackEntry ->
            val hashtag = java.net.URLDecoder.decode(
                backStackEntry.arguments?.getString("hashtag") ?: "",
                "UTF-8")

            HashtagScreen(
                hashtag = hashtag,
                onNavigateBack = { navController.navigateUp() },
            )
        }

    }
}
