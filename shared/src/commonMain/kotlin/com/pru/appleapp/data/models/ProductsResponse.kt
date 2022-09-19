package com.pru.appleapp.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    @SerialName("limit")
    val limit: Int? = null,
    @SerialName("products")
    val products: List<Product>? = null,
    @SerialName("skip")
    val skip: Int? = null,
    @SerialName("total")
    val total: Int? = null
)