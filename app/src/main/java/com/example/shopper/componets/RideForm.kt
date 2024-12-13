package com.example.shopper.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopper.models.DriverDetails
import com.example.shopper.models.RidesDetails
import com.example.shopper.screen.ConfirmationScreen
import com.example.shopper.viewmodel.RideViewModel
import com.example.shopper.viewmodel.ShopperViewModel


@Composable
fun RideForm(
    dadosViewModel: ShopperViewModel,
    viewModel: RideViewModel,
    navigateToNextScreen: () -> Unit,
    navigateToScreen: () -> Unit,
) {
    val origin =
        remember { mutableStateOf("") }
    val destination =
        remember { mutableStateOf("") }
    val customerId = remember { mutableStateOf(dadosViewModel.userId) }
    val selectedDriverName =
        remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val state = rememberScrollState()
    val rideData = viewModel.rideEstimate.value
    val scaffoldState = rememberScaffoldState()
    val selectedIndex = remember { mutableStateOf(0) }
    var confirmationResponse by remember { mutableStateOf<String?>(null) }
    val KeyMaps = String

    Column(modifier = Modifier.fillMaxSize()) {
        //== NavBar posicionada de maneira fixa no rodapé da pagina.
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                BottomNavigation(backgroundColor = Color(0xFF2A7592)) {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Home",
                                tint = Color(0xFF10100F)
                            )
                        },
                        label = {
                            Text(
                                "Home",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        },
                        selected = selectedIndex.value == 0,
                        onClick = {
                            navigateToNextScreen()
                        }
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Configuração",
                                tint = Color(0xFF10100F)
                            )
                        },
                        label = {
                            Text(
                                "Configuração",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        },
                        selected = selectedIndex.value == 0,
                        onClick = {
                            selectedIndex.value = 0
                        }
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Historico",
                                tint = Color(0xFF10100F)
                            )
                        },
                        label = {
                            Text(
                                "Historico",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        },
                        selected = selectedIndex.value == 0,
                        onClick = {
                            navigateToScreen()
                            selectedIndex.value = 0
                        }
                    )

                }
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .verticalScroll(state)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        ShopperTextField(
                            widthFloat = 1f,
                            textAlign = TextAlign.Left,
                            maxLength = 150,
                            refValue = customerId,
                            onValueChange = {
                                dadosViewModel.userId = it
                            },
                            placeholder = "Digite a ID do motorista",
                            tipoText = KeyboardType.Text
                        )

                        ShopperTextField(
                            widthFloat = 1f,
                            textAlign = TextAlign.Left,
                            maxLength = 150,
                            refValue = origin,
                            onValueChange = {
                                origin.value
                            },
                            placeholder = "Digite a origem",
                            tipoText = KeyboardType.Text
                        )
                        ShopperTextField(
                            widthFloat = 1f,
                            textAlign = TextAlign.Left,
                            maxLength = 150,
                            refValue = destination,
                            onValueChange = {
                                destination.value
                            },
                            placeholder = "Digite o destino",
                            tipoText = KeyboardType.Text
                        )
                    }

                    rideData.map { ride ->
                        ride?.origin
                        StaticMapScreen(
                            latitudeA = ride?.origin?.latitude!!,
                            longitudeA = ride.origin.longitude!!,
                            latitudeB = ride.destination?.latitude!!,
                            longitudeB = ride.destination.longitude!!,
                            apiKey = "${KeyMaps}"
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        rideData.map { ride ->
                            ride?.options?.map { driver ->
                                DriverCard(
                                    nameDriver = driver.name!!,
                                    vehicle = driver.vehicle!!,
                                    valuePrice = driver.value.toString(),
                                    onClick = {
                                        // Armazena o nome do motorista selecionado
                                        selectedDriverName.value = driver.name
                                        ride.distance?.let { distance ->
                                            ride.duration?.let { duration ->
                                                driver.id?.let { driverId ->
                                                    DriverDetails(
                                                        id = driverId,
                                                        name = driver.name
                                                    )?.let { driverDetails ->
                                                        driver.value?.let { value ->
                                                            viewModel.confirmRide(
                                                                context,
                                                                customerId = "1",
                                                                origin = (ride.origin?.latitude?.plus(
                                                                    ride.origin.longitude!!
                                                                )).toString(),
                                                                destination = (ride.destination?.latitude?.plus(
                                                                    ride.destination.longitude!!
                                                                )).toString(),
                                                                distance = distance,
                                                                duration = duration,
                                                                driver = driverDetails,
                                                                value = value,
                                                                onSuccess = { response ->
                                                                    confirmationResponse =
                                                                        "Success: $response"
                                                                    println("Success: $response")
                                                                }
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                viewModel.fetchRideEstimate(
                                    context,
                                    customerId = customerId.value,
                                    origin = origin.value,
                                    destination = destination.value
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF2A7693)
                            ),
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(0.8f)
                                .height(60.dp)
                        ) {
                            Text(text = "Buscar Rota", fontSize = 22.sp, color = Color(0xFF09202D))
                        }
                    }
                }
                // Exibe a tela de confirmação com o nome do motorista selecionado
                selectedDriverName.value?.let { driverName ->
                    ConfirmationScreen(
                        name = driverName,
                        onConfirm = { navigateToNextScreen() },
                        confirmationResponse = confirmationResponse
                    )
                }
            }
        )
    }
}