package com.example.codemobilechallengeapp.core.remote.repository

import com.example.codemobilechallengeapp.core.entity.Product
import com.example.codemobilechallengeapp.core.remote.network.HomeApiService
import com.example.codemobilechallengeapp.core.remote.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepositoryImpl(
    private val homeApiService: HomeApiService,
) : HomeRepository {

    override suspend fun getProductList() = withContext(Dispatchers.IO) {
        try {
            val response = homeApiService.getProductList()
            if (response.isSuccessful) {
                response.body()?.let {
                    val list = mutableListOf<Product>()
                    it.let { products ->
                        products.products.map { product ->
                            list.add(product.toProduct())
                        }
                    }
                    return@withContext Result.Success(list)
                }
            }
            return@withContext Result.Error(Exception(response.message()))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun getProduct(id: String) = withContext(Dispatchers.IO) {
        try {
            val response = homeApiService.getProduct(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    it.let { product ->
                        return@withContext Result.Success(product.toProduct())
                    }
                }
            }
            return@withContext Result.Error(Exception(response.message()))
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

}