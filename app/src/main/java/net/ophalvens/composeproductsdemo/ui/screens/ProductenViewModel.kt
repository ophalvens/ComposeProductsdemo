package net.ophalvens.composeproductsdemo.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.ophalvens.composeproductsdemo.network.ApiResponseProductsGet
import net.ophalvens.composeproductsdemo.network.Product
import net.ophalvens.composeproductsdemo.network.ProductenApi
import java.io.IOException

sealed interface ProductenUiState {
    data class Success(val data: ApiResponseProductsGet) : ProductenUiState
    object Error: ProductenUiState
    object Loading: ProductenUiState
}

class ProductenViewModel : ViewModel(){
    var productenUiState: ProductenUiState by mutableStateOf(ProductenUiState.Loading)
        private set
    lateinit var producten: List<Product>
        private set

    init {
        getProductenFromService()
    }

    fun getProductenFromService() {
        viewModelScope.launch {
            productenUiState = try {
                val response = ProductenApi.retrofitService.getProducten()
                producten = response.data
                ProductenUiState.Success(response)
            } catch(e: IOException) {
                ProductenUiState.Error
            }
        }
    }
}

