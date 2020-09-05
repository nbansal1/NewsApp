package com.codingwithnaman.newsapp

import android.app.Application
import androidx.room.Room
import com.codingwithnaman.newsapp.api.NewsAPI
import com.codingwithnaman.newsapp.db.ArticleDatabase
import com.codingwithnaman.newsapp.di.*
import com.codingwithnaman.newsapp.repository.NewsRepository
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit

class NewsApplication : Application() {

        override fun onCreate() {
        super.onCreate()
            startKoin {
                androidLogger()
                androidContext(this@NewsApplication)
                modules(
                    listOf(apiModule, viewModelModule, repositoryModule, networkModule, databaseModule)
                )
            }
    }
}