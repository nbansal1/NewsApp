package com.codingwithnaman.newsapp.di

import com.codingwithnaman.newsapp.ui.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        NewsViewModel(get(), get())
    }
}