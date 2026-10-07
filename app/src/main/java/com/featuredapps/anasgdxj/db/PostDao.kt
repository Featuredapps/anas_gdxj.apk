package com.featuredapps.anasgdxj.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.featuredapps.anasgdxj.model.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM posts ORDER BY id DESC")
    suspend fun getAll(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Query("DELETE FROM posts")
    suspend fun clear()
}
