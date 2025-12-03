package com.bytedance.croissantapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.bytedance.croissantapp.data.local.entity.PostEntity;

import java.util.List;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<PostEntity> posts);

    @Query("SELECT * FROM posts ORDER BY post_id DESC")
    List<PostEntity> getAllPosts();

    @Query("SELECT * FROM posts WHERE post_id = :id")
    PostEntity findPostById(String id);

    @Query("DELETE FROM posts")
    void deleteAll();

    @Query("SELECT * FROM posts LIMIT :count")
    List<PostEntity> getLatestPosts(int count);

    /**
     * 清空表并插入新数据
     */
    @Transaction
    default void replaceAll(List<PostEntity> posts) {
        deleteAll();
        insert(posts);
    }
}
