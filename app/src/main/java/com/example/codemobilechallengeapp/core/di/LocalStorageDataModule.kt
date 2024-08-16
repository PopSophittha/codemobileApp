package com.example.codemobilechallengeapp.core.di

import com.example.codemobilechallengeapp.core.storage.PreferenceRepository
import com.example.codemobilechallengeapp.core.storage.PreferenceRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localStorageModule = module {
    factory<PreferenceRepository> { PreferenceRepositoryImpl(androidContext()) }
}

