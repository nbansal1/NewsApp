package com.codingwithnaman.newsapp.repository

import com.codingwithnaman.newsapp.api.NewsAPI
import com.codingwithnaman.newsapp.db.ArticleDao
import com.codingwithnaman.newsapp.model.Article

class NewsRepository(
    val database : ArticleDao,
    val api: NewsAPI
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews(countryCode,pageNumber)

    suspend fun upsert(articleList: List<Article>) = database.upsert(articleList)

    suspend fun getOfflineBreakingNews()     = database.getAllArticles()
}