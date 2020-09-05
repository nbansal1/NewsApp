package com.codingwithnaman.newsapp.repository

import com.codingwithnaman.newsapp.api.NewsAPI
import com.codingwithnaman.newsapp.api.RetrofitInstance
import com.codingwithnaman.newsapp.db.ArticleDao
import com.codingwithnaman.newsapp.db.ArticleDatabase
import com.codingwithnaman.newsapp.model.Article

class NewsRepository(
    val dao : ArticleDao,
    val api: NewsAPI
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews(countryCode,pageNumber)

    suspend fun upsert(articleList: List<Article>) {
        dao.upsert(articleList)
    }

    fun getOfflineBreakingNews() = dao.getAllArticles()
}