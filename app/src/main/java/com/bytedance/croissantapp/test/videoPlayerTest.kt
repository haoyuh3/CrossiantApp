package com.bytedance.croissantapp.test

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bytedance.croissantapp.presentation.components.VideoPlayer // 确保引入您自己创建的 VideoPlayer

@Composable
fun VideoScreen() {
    // 准备一个视频 URL (请替换成一个真实可用的视频链接)
    val videoUrl = "https://lf3-static.bytednsdoc.com/obj/eden-cn/219eh7pbyphrnuvk/college_training_camp/item_videos/item_video139.mp4"

    // 直接调用您创建的 VideoPlayer 组件
    VideoPlayer(
        videoUrl = videoUrl,
        modifier = Modifier.fillMaxSize() // 让播放器铺满整个屏幕
    )
}

// 这是为了在 Android Studio 中预览效果
@Preview
@Composable
fun VideoScreenPreview() {
    VideoScreen()
}
