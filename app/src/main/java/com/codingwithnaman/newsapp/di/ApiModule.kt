package com.codingwithnaman.newsapp.di

import com.codingwithnaman.newsapp.api.NewsAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideBreakingNewsApi(retrofit: Retrofit): NewsAPI {
    return retrofit.create(NewsAPI::class.java)
}
    single { provideBreakingNewsApi(get()) }
}