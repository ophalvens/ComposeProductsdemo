package net.ophalvens.composeproductsdemo.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseProductsGet(
    @SerialName(value = "data") val data: List<Product>,
    // Status is mogelijk niet aanwezig in sommige returns :
    // in de API geeft enkel deliver_response() deze gegevens terug
    // terwijl deliver_JSONresponse() enkel data teruggeeft
    //
    // De meest propere oplossing zou zijn om in de API een aanpassing te doen
    // aan deliver_JSONresponse(), zodat die ook de status en code velden teruggeeft
    @SerialName(value = "status") val status: String? = null,
    @SerialName(value = "code") val code: String? = null

)