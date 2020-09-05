package com.codingwithnaman.newsapp.di

import android.content.Context
import com.codingwithnaman.newsapp.api.NewsAPI
import com.codingwithnaman.newsapp.db.ArticleDao
import com.codingwithnaman.newsapp.repository.NewsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    fun provideCountryRepository(api: NewsAPI, context: Context, dao : ArticleDao): NewsRepository {
        return NewsRepository(dao, api)
    }
    single { provideCountryRepository(get(), androidContext(), get()) }
}