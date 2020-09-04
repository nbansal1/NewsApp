package com.codingwithnaman.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codingwithnaman.newsapp.api.RetrofitInstance
import com.codingwithnaman.newsapp.db.ArticleDatabase
import com.codingwithnaman.newsapp.model.Article

class NewsRepository(
    val db : ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun upsert(articleList: List<Article>) {
        db.getArticleDao().upsert(articleList)
    }

    fun getOfflineBreakingNews() = db.getArticleDao().getAllArticles()

}