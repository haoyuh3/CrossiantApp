package com.bytedance.croissantapp

import android.app.Application
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

/**
 * 应用Application类
 * 使用Hilt进行依赖注入时必须创建此类
 *
 * @HiltAndroidApp 触发Hilt的代码生成，包括应用的基类
 */
@HiltAndroidApp
class CroissantApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // 初始化MMKV
        val rootDir = MMKV.initialize(this)
        println("MMKV初始化完成，根目录: $rootDir")
    }
}
