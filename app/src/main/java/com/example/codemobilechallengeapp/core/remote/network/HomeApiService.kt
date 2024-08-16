package com.example.codemobilechallengeapp.core.remote.network

import com.example.codemobilechallengeapp.core.remote.model.ProductResponse
import com.example.codemobilechallengeapp.core.remote.model.ProductsResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface HomeApiService {

    @GET("products")
    suspend fun getProductList(): Response<ProductsResponse>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: String): Response<ProductResponse>
}