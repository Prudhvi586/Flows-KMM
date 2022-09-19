package com.pru.appleapp.android.passengers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pru.appleapp.android.utils.Global.read
import com.pru.appleapp.data.models.Product
import com.pru.appleapp.data.models.ProductsResponse
import com.pru.appleapp.data.remote.APIService
import com.pru.appleapp.data.repository.RepositorySDK
import com.pru.appleapp.domain.repository.Repository
import com.pru.appleapp.utils.ApiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PassengersViewModel : ViewModel() {
    private val repositorySdk : Repository = RepositorySDK(apiService = APIService())
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    private val _productData = Channel<ApiState<Product>>()
    val productData: Flow<ApiState<Product>>
        get() = _productData.receiveAsFlow()

    private val _productsData = MutableStateFlow<ApiState<ProductsResponse>>(ApiState.Initial())
    val productsData: StateFlow<ApiState<ProductsResponse>>
        get() = _productsData

    init {
        getProducts()
    }

    fun getProduct() {
        viewModelScope.launch(dispatcher) {
            repositorySdk.getProduct().read(_productData)
        }
    }

    private fun getProducts() {
        viewModelScope.launch(dispatcher) {
            repositorySdk.getProducts().read(_productsData)
        }
    }
}