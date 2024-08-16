package com.example.codemobilechallengeapp.presentation.base

import android.app.Application
import com.example.codemobilechallengeapp.core.di.localStorageModule
import com.example.codemobilechallengeapp.core.di.networkingModule
import com.example.codemobilechallengeapp.core.di.repositoryModule
import com.example.codemobilechallengeapp.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CodeMobileApplication : Application() {

    private val presentationModule = listOf(viewModelModule)
    private val dataModules = listOf(networkingModule, repositoryModule, localStorageModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CodeMobileApplication)
            modules(dataModules + presentationModule)
        }
    }
}