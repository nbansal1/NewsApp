package com.codingwithnaman.newsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codingwithnaman.newsapp.repository.NewsRepository

class NewsViewModelProvider(
    val app : Application,
    val repository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, repository) as T
    }
}