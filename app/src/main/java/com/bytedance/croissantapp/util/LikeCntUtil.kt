package com.bytedance.croissantapp.util

object LikeCntUtil {
    /**
     * 格式化数量显示
     * 1000+ → 1k
     * 10000+ → 1w
     */
    fun formatCount(count: Int): String {
        return when {
            count >= 10000 -> "${count / 10000}w"
            count >= 1000 -> "${count / 1000}k"
            else -> count.toString()
        }
    }
}