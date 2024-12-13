package com.example.shopper.navagate

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopper.componets.HistoricRidesForm
import com.example.shopper.componets.OnboardForm
import com.example.shopper.componets.RideForm
import com.example.shopper.viewmodel.RideViewModel
import com.example.shopper.viewmodel.ShopperViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val shopperViewModel: ShopperViewModel = viewModel()

    NavHost(navController = navController, startDestination = "onboard") {
        composable("onboard") {
            val rideViewModel: RideViewModel = getViewModel()
            OnboardForm(
                dadosViewModel = shopperViewModel,
                navigateToNextScreen = { navController.navigate("rideScreen") }
            )
        }
        composable("rideScreen") {
            val rideViewModel: RideViewModel = getViewModel()
            RideForm(dadosViewModel = shopperViewModel,viewModel = rideViewModel,navigateToNextScreen = { navController.navigate("onboard")}, navigateToScreen = {navController.navigate("historicScreen")})
        }
        composable("historicScreen") {
            val rideViewModel: RideViewModel = getViewModel()
            HistoricRidesForm(dadosViewModel = shopperViewModel,viewModel = rideViewModel,navigateToNextScreen = { navController.navigate("onboard")},  )
        }
    }
}