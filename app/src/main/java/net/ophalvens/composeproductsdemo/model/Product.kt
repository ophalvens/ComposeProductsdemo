package net.ophalvens.composeproductsdemo.model

data class Product (
    val id: Int,
    val categorieId: Int, // TODO: ProductCategory aanmaken
    val naam: String,
    val prijs: Double,
    val categorie: String
)

data class ProductCategorie (
    val productCategoryId: Int,
    val omschrijving: String
)

