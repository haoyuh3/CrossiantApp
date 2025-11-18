package com.bytedance.crossiantapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 应用Application类
 * 使用Hilt进行依赖注入时必须创建此类
 *
 * @HiltAndroidApp 触发Hilt的代码生成，包括应用的基类
 */
@HiltAndroidApp
class CrossiantApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // TODO: 在这里初始化第三方库
        // 例如：ARouter, MMKV, Fresco等
    }
}
