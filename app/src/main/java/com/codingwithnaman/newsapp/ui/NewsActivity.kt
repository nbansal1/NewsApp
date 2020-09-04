package com.codingwithnaman.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.codingwithnaman.newsapp.R
import com.codingwithnaman.newsapp.db.ArticleDatabase
import com.codingwithnaman.newsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val repository = NewsRepository(ArticleDatabase.invoke(this))
        val viewModelProvider = NewsViewModelProvider(application, repository)
        viewModel = ViewModelProvider(this, viewModelProvider).get(NewsViewModel::class.java)
    }
}