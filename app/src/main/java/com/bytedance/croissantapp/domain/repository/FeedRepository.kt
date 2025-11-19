package com.bytedance.croissantapp.domain.repository

import com.bytedance.croissantapp.domain.model.Post

/**
 * Feed数据仓库接口
 */
interface FeedRepository {
    suspend fun getFeed(count: Int, acceptVideoClip: Boolean = false): Result<List<Post>>
}