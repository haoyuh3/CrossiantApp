package com.bytedance.croissantapp.util

/**
 * Application-wide constants
 */
object Constants {
    const val BASE_URL = "https://college-training-camp.bytedance.com/"

    // Network
    const val CONNECT_TIMEOUT = 10000L
    const val READ_TIMEOUT = 30000L
    const val WRITE_TIMEOUT = 30000L

    // Paging
    const val PAGE_SIZE = 20

    // Share Preference Keys
    const val KEY_LIKED_POSTS = "liked_posts"
    const val KEY_FOLLOWED_USERS = "followed_users"
    const val KEY_LIKE_PREFIX = "like_status_"
    const val KEY_FOLLOW_PREFIX = "follow_status_"
    const val KEY_MUSIC_MUTE = "music_mute_status"
    const val KEY_USER_NICKNAME = "user_nickname"
    const val KEY_USER_BIO = "user_bio"
    const val KEY_USER_AVATAR = "user_avatar"

    const val APP_PREFS = "croissant_prefs"

    const val DEFAULT_NICKNAME = "用户昵称"

    const val DEFAULT_USER_BIO = "这里是个人简介"

}