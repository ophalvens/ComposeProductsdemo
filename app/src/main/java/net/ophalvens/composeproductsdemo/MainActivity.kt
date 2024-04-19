package net.ophalvens.composeproductsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.ophalvens.composeproductsdemo.ui.screens.ProductsDemoApp
import net.ophalvens.composeproductsdemo.ui.theme.ComposeProductsDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProductsDemoTheme {
                ProductsDemoApp()
            }
        }
    }
}