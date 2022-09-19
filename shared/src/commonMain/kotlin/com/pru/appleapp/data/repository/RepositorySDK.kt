package com.pru.appleapp.data.repository

import com.pru.appleapp.data.models.Product
import com.pru.appleapp.data.models.ProductsResponse
import com.pru.appleapp.data.remote.APIService
import com.pru.appleapp.domain.repository.Repository
import com.pru.appleapp.domain.repository.Url
import com.pru.appleapp.utils.ApiState
import kotlinx.coroutines.flow.Flow


class RepositorySDK(private val apiService: APIService) : Repository {

    override suspend fun getProduct(url: Url): Flow<ApiState<Product>> {
        return apiService.call(url = url.get)
    }

    override suspend fun getProducts(url: Url): Flow<ApiState<ProductsResponse>> {
        return apiService.call(url = url.get)
    }
}