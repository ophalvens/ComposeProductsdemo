package net.ophalvens.composeproductsdemo.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Product (
    @SerialName(value = "PR_ID")
    val id: Int,
    @SerialName(value = "PR_naam")
    val naam: String,
    @SerialName(value = "PR_prijs")
    val prijs: Double,
    @SerialName(value = "CT_OM")
    val categorie: String,
    @SerialName(value = "PR_CT_ID")
    val categorieId: Int
)

