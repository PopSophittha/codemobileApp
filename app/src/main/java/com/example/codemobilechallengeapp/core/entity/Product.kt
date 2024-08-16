package com.example.codemobilechallengeapp.core.entity

import android.os.Parcel
import android.os.Parcelable

data class Product(
    val id: String?,
    val title: String?,
    val description: String?,
    val category: String?,
    val price: Double?,
    val totalPricePerEach: Double?,
    val discountPercentage: Double?,
    val rating: Double?,
    val stock: Int?,
    val images: List<String?>?,
    val thumbnail: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createStringArrayList(),
        parcel.readString()
    ) {
    }

    fun toEntity(): Product {
        return Product(
            id = id,
            title = title,
            description = description,
            category = category,
            discountPercentage = discountPercentage,
            rating = rating,
            totalPricePerEach = totalPricePerEach,
            price = price,
            stock = stock,
            images = images,
            thumbnail = thumbnail
        )
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(category)
        parcel.writeValue(price)
        parcel.writeValue(discountPercentage)
        parcel.writeValue(rating)
        parcel.writeValue(stock)
        parcel.writeStringList(images)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

}