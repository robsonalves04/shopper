package com.example.shopper.navagate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopper.componets.DriverCard
import com.example.shopper.componets.DriverDetailsCard
import com.example.shopper.componets.OnboardForm
import com.example.shopper.componets.RideForm
import com.example.shopper.componets.SearchForm
import com.example.shopper.models.Driver
import com.example.shopper.models.RideEstimateModel
import com.example.shopper.viewmodel.RideViewModel
import com.example.shopper.viewmodel.ShopperViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavigation(navController: NavHostController, shopperViewModel: ShopperViewModel) {
    val navController = rememberNavController()
    val shopperViewModel: ShopperViewModel = viewModel()

    NavHost(navController = navController, startDestination = "onboard") {
        composable("onboard") {
            OnboardForm(
                dadosViewModel = shopperViewModel,
                navigateToNextScreen = { navController.navigate("rideScreen") }
            )
        }
        composable("rideScreen") {
            val rideViewModel: RideViewModel = getViewModel()
            RideForm(viewModel = rideViewModel,navigateToNextScreen = { navController.navigate("driverDatails")})
        }
        composable("driverDatails") {
            val rideViewModel: RideViewModel = getViewModel()
            DriverDetailsCard(rideViewModel,model = RideEstimateModel(), )
//            DriverCardWithDetailsNavigation(driver = driver,rideDetails = rideDetails )
        }
        composable("search") {
            SearchForm(dadosViewModel = shopperViewModel)
        }

        composable(
            route = "driverDetails/{name}/{vehicle}/{price}/{distance}/{driverId}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("vehicle") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType },
                navArgument("distance") { type = NavType.IntType },
                navArgument("driverId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: "N/A"
            val vehicle = backStackEntry.arguments?.getString("vehicle") ?: "N/A"
            val price = backStackEntry.arguments?.getString("price") ?: "N/A"
            val distance = backStackEntry.arguments?.getInt("distance") ?: 0
            val driverId = backStackEntry.arguments?.getInt("driverId") ?: 0

//            DriverDetailsCard(
//                rideDetails = RideEstimateModel(),
//                driver = Driver(),
//                onBack = {}
//
//            )
        }
    }
}
//
//@Composable
//fun DriverCardWithDetailsNavigation(
//    driver: Driver,
//    rideDetails: RideEstimateModel
//) {
//    var showDetails by remember { mutableStateOf(false) }
//
//    if (showDetails) {
//        DriverDetailsCard(
//            driver = driver,
//            rideDetails = rideDetails,
//            onBack = { showDetails = false }
//        )
//    } else {
//        DriverCard(
//            nameDriver = driver.name ?: "Motorista Desconhecido",
//            vehicle = "Carro modelo desconhecido", // Adapte conforme os dados disponíveis
//            valuePrice = rideDetails.value?.toString() ?: "N/A",
//            onClick = { showDetails = true }
//        )
//    }
//}



