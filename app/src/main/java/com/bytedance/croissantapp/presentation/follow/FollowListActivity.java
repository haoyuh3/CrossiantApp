package com.bytedance.croissantapp.presentation.follow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.croissantapp.R;
import com.bytedance.croissantapp.data.model.FollowUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注列表页面
 */
public class FollowListActivity extends Activity {

    private RecyclerView rvFollowList;
    private LinearLayout layoutEmpty;
    private TextView tvFollowCount;
    private ImageView btnBack;

    private FollowListAdapter adapter;
    private List<FollowUser> followUserList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_list);

        initViews();
        initData();
        setupRecyclerView();
        updateUI();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        rvFollowList = findViewById(R.id.rv_follow_list);
        layoutEmpty = findViewById(R.id.layout_empty);
        tvFollowCount = findViewById(R.id.tv_follow_count);
        btnBack = findViewById(R.id.btn_back);

        // 返回按钮点击事件
        btnBack.setOnClickListener(v -> finish());
    }

    /**
     * 初始化数据
     * 这里使用模拟数据，实际项目中应该从网络或数据库获取
     */
    private void initData() {
        followUserList = new ArrayList<>();

        followUserList.add(new FollowUser("1", "小红", "热爱生活，热爱摄影", "", true));
        followUserList.add(new FollowUser("2", "旅行家Tom", "走遍世界的每一个角落", "", true));
        followUserList.add(new FollowUser("3", "美食达人", "探索城市中的美味佳肴", "", true));
        followUserList.add(new FollowUser("4", "健身教练Mike", "专业健身指导，科学塑形", "", true));
        followUserList.add(new FollowUser("5", "摄影师Anna", "用镜头记录生活的美好瞬间", "", true));
        followUserList.add(new FollowUser("6", "时尚博主Lisa", "分享最新时尚潮流趋势", "", true));
        followUserList.add(new FollowUser("7", "读书人老王", "每天一本好书推荐", "", true));
        followUserList.add(new FollowUser("8", "音乐制作人", "原创音乐，分享音乐故事", "", true));
    }

    /**
     * 设置RecyclerView
     */
    private void setupRecyclerView() {
        adapter = new FollowListAdapter();
        adapter.setUserList(followUserList);

        // 设置关注按钮点击监听
        adapter.setFollowClickListener(this::handleFollowClick);

        rvFollowList.setLayoutManager(new LinearLayoutManager(this));
        rvFollowList.setAdapter(adapter);
    }

    /**
     * 处理关注/取消关注操作
     */
    private void handleFollowClick(FollowUser user, int position) {
        // 切换关注状态
        boolean newFollowState = !user.isFollowing();
        user.setFollowing(newFollowState);

        // 更新列表项
        adapter.updateUser(position);

        // 显示提示
        String message = newFollowState ? "已关注 " + user.getUsername() : "已取消关注 " + user.getUsername();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // 更新计数
        updateUI();
    }

    /**
     * 更新UI显示
     */
    private void updateUI() {
        // 计算当前关注的人数
        int followingCount = 0;
        for (FollowUser user : followUserList) {
            if (user.isFollowing()) {
                followingCount++;
            }
        }

        // 更新关注计数
        tvFollowCount.setText("关注 " + followingCount);

        // 根据列表是否为空显示不同的UI
        if (followingCount == 0) {
            rvFollowList.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);
        } else {
            rvFollowList.setVisibility(View.VISIBLE);
            layoutEmpty.setVisibility(View.GONE);
        }
    }
}
