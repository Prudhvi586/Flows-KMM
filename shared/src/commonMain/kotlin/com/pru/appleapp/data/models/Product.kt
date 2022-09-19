package com.pru.appleapp.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("brand")
    val brand: String? = null,
    @SerialName("category")
    val category: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("discountPercentage")
    val discountPercentage: Double? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("images")
    val images: List<String>? = null,
    @SerialName("price")
    val price: Int? = null,
    @SerialName("rating")
    val rating: Double? = null,
    @SerialName("stock")
    val stock: Int? = null,
    @SerialName("thumbnail")
    val thumbnail: String? = null,
    @SerialName("title")
    val title: String? = null
)