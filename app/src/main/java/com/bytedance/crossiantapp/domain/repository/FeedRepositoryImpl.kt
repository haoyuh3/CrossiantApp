package com.bytedance.crossiantapp.domain.repository

import com.bytedance.crossiantapp.data.model.toDomain
import com.bytedance.crossiantapp.data.remote.FeedApi
import com.bytedance.crossiantapp.domain.model.Post
import com.bytedance.crossiantapp.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * Feed数据仓库实现
 */
class FeedRepositoryImpl @Inject constructor(
    private val feedApi: FeedApi
) : FeedRepository {

    override suspend fun getFeed(count: Int, acceptVideoClip: Boolean): Result<List<Post>> {
        return try {
            val response = feedApi.getFeed(count, acceptVideoClip)

            if (response.statusCode == 0 && response.postList != null) {
                val posts = response.postList.map { it.toDomain() }
                Result.success(posts)
            } else {
                Result.failure(Exception("API返回错误: ${response.statusCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}