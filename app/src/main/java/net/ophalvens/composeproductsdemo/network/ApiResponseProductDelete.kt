package net.ophalvens.composeproductsdemo.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseProductDelete(

    // Dit is quasi dezelfde als de ApiResponseProductAdd
    // Deze zouden dezelfde class kunnen zijn als er verder geen wijzigingen komen.
    // Een mogelijke wijziging :
    // - in de add response zou op de server het net aangemaakte Product (of de id ervan) in response
    //   verwerkt kunnen worden, zodat je niet noodzakelijk alle Producten opnieuw moet ophalen


    // enkel status is altijd aanwezig
    // zie https://stackoverflow.com/questions/68192794/make-a-field-optional-in-kotlinx-serialization hoe je kan omgaan met ontbrekende velden

    @SerialName(value = "status") val status: String, // "ok" of "fail"
    @SerialName(value = "data") val data: String? = null, // enkel indien status "ok"
    @SerialName(value = "error") val error: String? = null, // enkel indien status "fail"
    @SerialName(value = "errNo") val errNo: Int? = null, // enkel indien status "fail"
    @SerialName(value = "mysqlError") val mysqlError: String? = null, // enkel indien status "fail"
    @SerialName(value = "message") val message: String? = null, // enkel indien status "fail"


)