package com.pru.appleapp.domain.repository

import com.pru.appleapp.data.models.Product
import com.pru.appleapp.data.models.ProductsResponse
import com.pru.appleapp.utils.ApiState
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getProduct(url: Url = Url(endPoint = "/products/1")): Flow<ApiState<Product>>


    suspend fun getProducts(url: Url = Url(endPoint = "/products")): Flow<ApiState<ProductsResponse>>
}