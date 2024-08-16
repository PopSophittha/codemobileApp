package com.example.codemobilechallengeapp.core.di

import com.example.codemobilechallengeapp.presentation.main.DetailViewModel
import com.example.codemobilechallengeapp.presentation.main.HomeViewModel
import com.example.codemobilechallengeapp.presentation.main.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}