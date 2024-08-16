package com.example.codemobilechallengeapp.core.remote.network.interceptor

import com.example.codemobilechallengeapp.core.remote.datasource.GetMyAccessTokenDataSource
import com.example.codemobilechallengeapp.core.remote.model.Result
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor for adding required headers to web service request to The One API.
 */
class AddHeadersInterceptor(private val getMyAccessTokenDataSource: GetMyAccessTokenDataSource) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        // Content-Type
        builder.addHeader("Content-Type", "application/json")

        // Authorization
//        when (val result = getMyAccessTokenDataSource.getMyAccessToken()) {
//            is Result.Success -> {
//                result.data?.let { accessToken ->
//                    builder.addHeader("Authorization", "Bearer $accessToken")
//                }
//            }
//            else -> {}
//        }

        return chain.proceed(builder.build())
    }
}