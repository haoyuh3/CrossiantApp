package com.bytedance.croissantapp.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bytedance.croissantapp.domain.model.Post
import com.bytedance.croissantapp.presentation.detail.components.DetailTopBar
import com.bytedance.croissantapp.presentation.detail.components.DetailBottomBar
import com.bytedance.croissantapp.presentation.detail.components.DetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    postId: String,
    initialPost: Post? = null,
    onNavigateBack: () -> Unit,
    onHashtagClick: (String) -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    // 加载作品详情
    LaunchedEffect(postId, initialPost) {
        if (initialPost != null) {
            viewModel.setInitialPost(initialPost)
        } else {
            println("错误: Post对象不存在")
        }
    }

    // 观察UI状态
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            when (val state = uiState) {
                is DetailUiState.Success -> {
                    DetailTopBar(
                        post = state.post,
                        onNavigateBack = onNavigateBack,
                        onFollowClick = { viewModel.toggleFollow() }
                    )
                }
                else -> {
                    // 加载中或错误时显示简单的TopBar
                    TopAppBar(
                        title = { Text("作品详情") },
                        navigationIcon = {
                            IconButton(onClick = onNavigateBack) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, "返回")
                            }
                        }
                    )
                }
            }
        },
        bottomBar = {
            if (uiState is DetailUiState.Success) {
                DetailBottomBar(
                    post = (uiState as DetailUiState.Success).post,
                    onLikeClick = { viewModel.toggleLike() }
                )
            }
        },
        modifier = modifier
    ) { paddingValues ->
        // 内容区域
        when (val state = uiState) {
            is DetailUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is DetailUiState.Success -> {
                DetailContent(
                    post = state.post,
                    modifier = Modifier.padding(paddingValues),
                    onHashtagClick = onHashtagClick
                )
            }
            is DetailUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("加载失败: ${state.message}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {}) {
                            Text("重试")
                        }
                    }
                }
            }
        }
    }
}