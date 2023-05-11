package net.ophalvens.composeproductsdemo.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Product (
    @SerialName(value = "PR_ID") val id: Int,
    @SerialName(value = "PR_naam") val naam: String,
    @SerialName(value = "PR_prijs") val prijs: Double,
    @SerialName(value = "CT_OM") val categorie: String,
    @SerialName(value = "PR_CT_ID") val categorieId: Int

    /*
    De achterliggende query in de API is :

    SELECT PR_ID, PR_CT_ID, PR_naam, PR_prijs, CT_OM
    FROM producten2
    JOIN categorieen
        ON PR_CT_ID = CT_ID

     */
)

