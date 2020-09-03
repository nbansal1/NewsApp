package com.codingwithnaman.newsapp.repository

import com.codingwithnaman.newsapp.api.RetrofitInstance
import com.codingwithnaman.newsapp.db.ArticleDatabase

class NewsRepository(
    val db : ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
}