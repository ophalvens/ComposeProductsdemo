package net.ophalvens.composeproductsdemo.data

import net.ophalvens.composeproductsdemo.model.Product

class repository {
    // TODO: vervang uiteindelijk door Retrofit call
    companion object {
        val producten = listOf<Product>(
            Product(1,1, "Appel", 5.5, "Fruit"),
            Product(2,1,"Peer", 5.0,"Fruit")
        )
    }
}