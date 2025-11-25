package com.bytedance.croissantapp.data.model;

/**
 * 关注用户数据模型
 */
public class FollowUser {
    private String userId;
    private String username;
    private String bio;
    private String avatarUrl;
    private boolean isFollowing;

    public FollowUser(String userId, String username, String bio, String avatarUrl, boolean isFollowing) {
        this.userId = userId;
        this.username = username;
        this.bio = bio;
        this.avatarUrl = avatarUrl;
        this.isFollowing = isFollowing;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }
}
