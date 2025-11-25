package com.bytedance.croissantapp.presentation.follow

import android.content.Context
import android.content.Intent

/**
 * 启动关注列表Activity的工具类
 * 用于从Compose界面跳转到Java实现的Activity
 */
object FollowListLauncher {

    /**
     * 启动关注列表页面
     */
    fun launch(context: Context) {
        val intent = Intent(context, FollowListActivity::class.java)
        context.startActivity(intent)
    }
}
