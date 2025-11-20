package com.bytedance.croissantapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bytedance.croissantapp.presentation.home.HomeTabItem

/**
 * 首页顶部Tab栏
 *
 * @param selectedTab 当前选中的Tab
 * @param onTabSelected 点击Tab的回调
 * @param modifier 修饰符
 */
@Composable
fun HomeTabRow(
    selectedTab: HomeTabItem,
    onTabSelected: (HomeTabItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        IconButton(
            onClick = { /* 设置功能暂不实现 */ },
            enabled = true,
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Menu,
                contentDescription = "菜单",
                tint = Color.Gray,
            )
        }
        // Tab列表
        HomeTabItem.entries.forEach { tab ->
            HomeTabButton(
                tab = tab,
                isSelected = selectedTab == tab,
                onClick = {
                    if (tab.isEnabled) {
                        onTabSelected(tab)
                    }
                },
            )
        }

        // 搜索图标
        IconButton(
            onClick = { /* 搜索功能暂不实现 */ },
            enabled = true,
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "搜索",
                tint = Color.Gray,
            )
        }
    }
}

/**
 * 单个Tab按钮
 */
@Composable
private fun HomeTabButton(
    tab: HomeTabItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable(enabled = tab.isEnabled) { onClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Tab文字
        Text(
            text = tab.title,
            style = MaterialTheme.typography.titleMedium,
            color = if (isSelected) Color.Black else Color.Gray,
            fontWeight =
                if (isSelected) {
                    androidx.compose.ui.text.font.FontWeight.Bold
                } else {
                    androidx.compose.ui.text.font.FontWeight.Normal
                },
            modifier = Modifier.padding(bottom = 4.dp),
        )

        // 选中时的下划线
        Box(
            modifier =
                Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .background(if (isSelected) Color.Gray else Color.White),
        )
    }
}
