package com.example.codemobilechallengeapp.core.remote.model

import com.example.codemobilechallengeapp.core.entity.Product
import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products") val products: List<ProductResponse>
)

data class ProductResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("price") val price: Double?,
    @SerializedName("discountPercentage") val discountPercentage: Double?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("stock") val stock: Int?,
    @SerializedName("images") val images: List<String?>?,
    @SerializedName("thumbnail") val thumbnail: String?,
) {
    fun toProduct() = Product(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        totalPricePerEach = (price ?: 0.0) * (stock ?: 0),
        stock = stock,
        images = images,
        thumbnail = thumbnail
    )
}