package net.ophalvens.composeproductsdemo.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseProductAdd(

    // enkel status is altijd aanwezig
    // zie https://stackoverflow.com/questions/68192794/make-a-field-optional-in-kotlinx-serialization hoe je kan omgaan met ontbrekende velden

    @SerialName(value = "status") val status: String, // "ok" of "fail"
    @SerialName(value = "data") val data: String? = null, // enkel indien status "ok"
    @SerialName(value = "error") val error: String? = null, // enkel indien status "fail"
    @SerialName(value = "errNo") val errNo: String? = null, // enkel indien status "fail"
    @SerialName(value = "mysqlError") val mysqlError: String? = null, // enkel indien status "fail"
    @SerialName(value = "message") val message: String? = null, // enkel indien status "fail"

)