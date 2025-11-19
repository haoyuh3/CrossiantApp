package com.bytedance.crossiantapp.domain.usecase

import com.bytedance.crossiantapp.domain.model.Post
import com.bytedance.crossiantapp.domain.repository.FeedRepository
import javax.inject.Inject

/**
 * 获取Feed流用例
 */
class GetFeedUseCase @Inject constructor(
    private val feedRepository: FeedRepository
) {
    suspend operator fun invoke(
        count: Int = 20,
        acceptVideoClip: Boolean = false
    ): List<Post> {
        return feedRepository.getFeed(count, acceptVideoClip)
            .getOrElse { emptyList() }
    }
}