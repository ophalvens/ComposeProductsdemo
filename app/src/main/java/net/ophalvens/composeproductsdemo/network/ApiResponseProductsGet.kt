package net.ophalvens.composeproductsdemo.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseProductsGet(
    @SerialName(value = "data")
    val data: List<Product>
)