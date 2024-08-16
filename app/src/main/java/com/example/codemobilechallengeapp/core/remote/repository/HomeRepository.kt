package com.example.codemobilechallengeapp.core.remote.repository

import com.example.codemobilechallengeapp.core.entity.Product
import com.example.codemobilechallengeapp.core.remote.model.Result

interface HomeRepository {
    suspend fun getProductList(): Result<MutableList<Product>>
    suspend fun getProduct(id: String): Result<Product>
}