package com.bytedance.croissantapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 作品（帖子）模型
 */
@Parcelize
data class Post(
    val postId: String,
    val title: String,
    val content: String,
    val hashtags: List<Hashtag>,
    val createTime: Long,
    val author: Author,
    val clips: List<Clip>,
    val music: Music?,
    val likeCount: Int = 0,         // 从sharePreference读取的本地状态
    val isLiked: Boolean = false    // 从sharePreference读取的本地状态
) : Parcelable

/**
 * 作者信息
 */
@Parcelize
data class Author(
    val userId: String,
    val nickname: String,
    val avatar: String,
    val isFollowed: Boolean = false  // 从sharePreference读取的本地状态
) : Parcelable

/**
 * 图片/视频片段
 */
@Parcelize
data class Clip(
    val type: ClipType,
    val width: Int,
    val height: Int,
    val url: String
) : Parcelable {
    /**
     * 计算显示用的宽高比
     * 根据设计要求，限制在 3:4 ~ 4:3 之间
     */
    val displayAspectRatio: Float
        get() {
            return when (type) {
                ClipType.VIDEO -> 9f / 16f  // 视频：竖屏比例
                ClipType.IMAGE -> {
                    // 图片比例计算逻辑
                    val ratio = width.toFloat() / height.toFloat()
                    ratio.coerceIn(3f / 4f, 4f / 3f)
                }
            }
        }
}

/**
 * 片段类型
 */
enum class ClipType {
    IMAGE,  // 图片
    VIDEO   // 视频
}

/**
 * 话题词位置信息
 */
@Parcelize
data class Hashtag(
    val start: Int,  // 起始下标（闭区间）
    val end: Int     // 终止下标（开区间）
) : Parcelable

/**
 * 背景音乐信息
 */
@Parcelize
data class Music(
    val volume: Int,
    val seekTime: Long,
    val url: String
) : Parcelable