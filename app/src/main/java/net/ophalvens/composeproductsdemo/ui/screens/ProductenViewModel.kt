package net.ophalvens.composeproductsdemo.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.ophalvens.composeproductsdemo.network.*
import java.io.IOException

sealed interface ProductenUiState {
    data class Success(val data: ApiResponseProductsGet) : ProductenUiState
    object Error: ProductenUiState
    object Loading: ProductenUiState
}
sealed interface ProductAddUiState {
    data class Success(val message: ApiResponseProductAdd) : ProductAddUiState
    object Error: ProductAddUiState
    object Loading: ProductAddUiState
}
sealed interface ProductDeleteUiState {
    data class Success(val message: ApiResponseProductDelete) : ProductDeleteUiState
    object Error: ProductDeleteUiState
    object Loading: ProductDeleteUiState
}


class ProductenViewModel : ViewModel(){
    // Producten ophalen
    var productenUiState: ProductenUiState by mutableStateOf(ProductenUiState.Loading)
        private set
    lateinit var producten: List<Product>
        private set

    // Product toevoegen
    var productAddUiState: ProductAddUiState by mutableStateOf(ProductAddUiState.Loading)
        private set
    lateinit var productAddResponseStatus: String
        private set

    //  Product verwijderen
    var productDeleteUiState: ProductDeleteUiState by mutableStateOf(ProductDeleteUiState.Loading)
        private set
    lateinit var productDeleteResponseStatus: String
        private set


    init {
        getProductenFromService()
    }

    fun getProductenFromService() {
        viewModelScope.launch {
            productenUiState = try {
                val response = ProductenApi.retrofitService.getProducten()
                // TODO: hou rekening met response.status en response.message zodra die zijn
                // toegevoegd.
                producten = response.data
                ProductenUiState.Success(response)
            } catch(e: IOException) {
                ProductenUiState.Error
            }
        }
    }

    fun addProductToService() {

        // Om dit voorbeeld eenvoudig te houden, maken we hier een Product aan om te verzenden, met willekeurige waarden
        // Waarschijnlijk wil je deze waarden eerder uit je UI halen.


        val nieuwProduct = ProductToSend(
            categorieId = (1..2).random(), // 2 mogelijke waarden : 1 of 2
            naam = "-aaa-${System.currentTimeMillis()}", // "herkenbare 'fake' naam"
            prijs = (100..10000).random() / 100.0 // willekeurige prijs
        )

        viewModelScope.launch {
            productAddUiState = try {
                val response = ProductenApi.retrofitService.addProduct(nieuwProduct)
                productAddResponseStatus = response.status

                if(productAddResponseStatus == "ok") {
                    // Product is toegevoegd, haal de lijst met producten opnieuw op
                    // om dit te kunnen tonen
                    getProductenFromService()
                }

                ProductAddUiState.Success(response)
            } catch(e: IOException) {
                ProductAddUiState.Error
            }
        }
    }

    fun deleteProductFromService(teVerwijderenProduct: Product) {

        viewModelScope.launch {
            productDeleteUiState = try {
                val response = ProductenApi.retrofitService.deleteProduct(teVerwijderenProduct)
                productDeleteResponseStatus = response.status

                if(productDeleteResponseStatus == "ok") {
                    // TODO : teVerwijderenProduct is verwijderd, haal het nu uit producten
                    // producten = producten.minus(teVerwijderenProduct)

                    // omdat de vorige lijn geen updates triggert (andere state die aan de Composables werd doorgegeven):
                    getProductenFromService()


                }

                ProductDeleteUiState.Success(response)
            } catch(e: IOException) {
                ProductDeleteUiState.Error
            }
        }
    }
}

