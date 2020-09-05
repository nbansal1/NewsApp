package com.codingwithnaman.newsapp.di

import android.app.Application
import androidx.room.Room
import com.codingwithnaman.newsapp.db.ArticleDao
import com.codingwithnaman.newsapp.db.ArticleDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): ArticleDatabase {
        return Room.databaseBuilder(
            application,
            ArticleDatabase::class.java,
            "article_db.db"
        ).build()
    }

    fun provideCountriesDao(database: ArticleDatabase): ArticleDao {
        return  database.getArticleDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}