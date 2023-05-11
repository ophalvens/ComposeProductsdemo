package net.ophalvens.composeproductsdemo.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProductToSend (
    // bevat de data voor een product dat we naar de API willen sturen
    @SerialName(value = "PR_naam") val naam: String,
    @SerialName(value = "PR_prijs") val prijs: Double,
    @SerialName(value = "PR_CT_ID") val categorieId: Int // 1 of 2 in voorbeeld API
)