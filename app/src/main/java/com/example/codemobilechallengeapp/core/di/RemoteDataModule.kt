package com.example.codemobilechallengeapp.core.di

import com.example.codemobilechallengeapp.core.remote.datasource.GetMyAccessTokenDataSource
import com.example.codemobilechallengeapp.core.remote.datasource.GetMyAccessTokenDataSourceImpl
import com.example.codemobilechallengeapp.core.remote.repository.HomeRepository
import com.example.codemobilechallengeapp.core.remote.repository.HomeRepositoryImpl
import com.example.codemobilechallengeapp.core.remote.repository.LoginRepository
import com.example.codemobilechallengeapp.core.remote.repository.LoginRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<HomeRepository> { HomeRepositoryImpl(get()) }
    factory<LoginRepository> { LoginRepositoryImpl(get()) }

    factory<GetMyAccessTokenDataSource> { GetMyAccessTokenDataSourceImpl(get()) }
}
