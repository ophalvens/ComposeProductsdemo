package net.ophalvens.composeproductsdemo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.ophalvens.composeproductsdemo.data.repository
import net.ophalvens.composeproductsdemo.network.Product
import net.ophalvens.composeproductsdemo.ui.theme.ComposeProductsDemoTheme

@Composable
fun Topbar(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(32.dp)
        .background(MaterialTheme.colors.primary)

) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Demo SuperDuper Compose",
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(MaterialTheme.colors.primaryVariant)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,

    ) {

        Button(
            onClick = { /* TODO */},
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Text(
                text = "Producten",
                color = MaterialTheme.colors.onPrimary
            )
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Text(
                text = "Nieuw Product",
                color = MaterialTheme.colors.onPrimary
            )
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Text(
                text = "About",
                color = MaterialTheme.colors.onPrimary
            )
        }

    }

}


@Preview
@Composable
fun ProductsDemoApp(
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = { Topbar() },
        bottomBar = { BottomNavBar() }
    ) {
        val productenViewModel: ProductenViewModel = viewModel()
        ProductenLijst(
            productenUiState = productenViewModel.productenUiState,
            modifier = Modifier.padding(it)
        )
    }
}


@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Text(text = "${product.naam} - €${product.prijs.toString()} - (${product.categorie})")
        }
    }

}

@Composable
fun ProductenLijst(
    productenUiState: ProductenUiState,
    modifier: Modifier = Modifier
) {
    if(productenUiState is ProductenUiState.Success) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
                .fillMaxSize()
        ){
            val producten = productenUiState.data.data
            items(producten){
                ProductCard(product = it)
            }
        }


    }

}


@Composable
fun ProductCardPreview() {
    ComposeProductsDemoTheme {
        //ProductCard(product = repository.producten[0])
    }
}