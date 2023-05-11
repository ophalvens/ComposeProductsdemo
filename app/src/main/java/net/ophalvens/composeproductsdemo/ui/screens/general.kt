package net.ophalvens.composeproductsdemo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.ophalvens.composeproductsdemo.R
import net.ophalvens.composeproductsdemo.network.Product

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
            text = stringResource(R.string.app_title),
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun BottomNavBar(
    productenViewModel: ProductenViewModel,
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
                text = stringResource(R.string.producten),
                color = MaterialTheme.colors.onPrimary
            )
        }

        Button(
            onClick = {
                // doe de call om een product toe te voegen.
                productenViewModel.addProductToService()
            },
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Text(
                text = stringResource(R.string.product_nieuw),
                color = MaterialTheme.colors.onPrimary
            )
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Text(
                text = stringResource(R.string.over),
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
    val productenViewModel: ProductenViewModel = viewModel()

    Scaffold(
        topBar = { Topbar() },
        bottomBar = { BottomNavBar(productenViewModel) }
    ) {

        ProductenLijst(
            productenUiState = productenViewModel.productenUiState,
            productenViewModel = productenViewModel,
            modifier = Modifier.padding(it)
        )
    }
}


@Composable
fun ProductCard(
    product: Product,
    // TODO : vervangen door het doorgeven van een lambda
    productenViewModel: ProductenViewModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp


    ) {
        Row(
            modifier = Modifier
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically



        ) {
            Column(

            ) {
                // 🍓 fruit (strawberry)
                // 🥕 vegetable (carrot)
                val cat = when(product.categorie) {
                    "Fruit" -> "🍓"
                    "Groenten" -> "🥕"
                    else -> "🥕"
                }
                Text(
                    text = "${cat} ${product.naam}"
                )
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        painter = painterResource(R.drawable.baseline_euro_24) ,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = stringResource(id = R.string.delete),
                        modifier = Modifier
                            .padding(4.dp)
                    )
                    Text(
                        text = "${product.prijs}"
                    )
                }

            }

            Column(

            ){
                IconButton(
                    onClick = {
                        productenViewModel.deleteProductFromService(product)
                    }) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_delete_24) ,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = stringResource(id = R.string.delete)
                    )
                }
            }


        }
    }

}

@Composable
fun ProductenLijst(
    productenUiState: ProductenUiState,
    productenViewModel: ProductenViewModel,
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
                // TODO : productenViewModel vervangen door een lambda
                ProductCard(product = it, productenViewModel)
            }
        }


    }

}


