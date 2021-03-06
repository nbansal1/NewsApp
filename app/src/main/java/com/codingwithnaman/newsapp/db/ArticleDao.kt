package com.codingwithnaman.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithnaman.newsapp.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(articleList: List<Article>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles() : List<Article>

}