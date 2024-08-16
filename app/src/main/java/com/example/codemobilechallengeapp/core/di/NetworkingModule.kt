package com.example.codemobilechallengeapp.core.di

import com.example.codemobilechallengeapp.core.remote.network.HomeApiService
import com.example.codemobilechallengeapp.core.remote.network.interceptor.AddHeadersInterceptor
import com.example.codemobilechallengeapp.core.remote.network.interceptor.UnauthorizedInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkingModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(AddHeadersInterceptor(get()))
            .addInterceptor(UnauthorizedInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
//                if (BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.BODY
            })
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        GsonBuilder()
            .setPrettyPrinting()
            .create()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
//            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(HomeApiService::class.java)
    }
}