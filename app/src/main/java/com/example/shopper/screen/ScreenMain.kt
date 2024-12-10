package com.example.shopper.screen

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.example.shopper.componets.OnboardForm
import com.example.shopper.navagate.AppNavigation
import com.example.shopper.viewmodel.ShopperViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScreenMain : AppCompatActivity()  {
    private val shopperViewModel: ShopperViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //== Formulario de Tela Inicial
            val navController = rememberNavController()

            // Configura a navegação do app
            AppNavigation(navController = navController, shopperViewModel = shopperViewModel)
        }
    }
}