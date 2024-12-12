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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopper.models.DriverDetails

import com.example.shopper.models.RideEstimateModel
import com.example.shopper.screen.ConfirmationScreen
import com.example.shopper.viewmodel.RideViewModel
import com.example.shopper.viewmodel.ShopperViewModel


@Composable
fun RideForm(dadosViewModel: ShopperViewModel, viewModel: RideViewModel, navigateToNextScreen: () -> Unit) {
    val origin =
        remember { mutableStateOf("Av. Thomas Edison, 365 - Barra Funda, São Paulo - SP, 01140-000") }
    val destination =
        remember { mutableStateOf("Av. Paulista, 1538 - Bela Vista, São Paulo - SP, 01310-200") }
    val customer_id = remember { mutableStateOf("") }
    val distance = remember { mutableStateOf("") }
    val custumerId = remember { mutableStateOf("") }

    val context = LocalContext.current
    val state = rememberScrollState()
    val rideData = viewModel.rideEstimate.value

    var confirmationResponse by remember { mutableStateOf<String?>(null) }

dadosViewModel.setUserData(id = "", "")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            ShopperTextField(
                widthFloat = 1f,
                textAlign = TextAlign.Left,
                maxLength = 150,
                refValue = customer_id,
                onValueChange = {
                    dadosViewModel.userId//
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
                placeholder = "Digite a destino",
                tipoText = KeyboardType.Text
            )
        }
        rideData.map { it ->
            it?.origin
            StaticMapScreen(
                latitudeA = it?.origin?.latitude!!,
                longitudeA = it?.origin?.longitude!!,
                latitudeB = it?.destination?.latitude!!,
                longitudeB = it?.destination?.longitude!!,
                apiKey = "AIzaSyB4JS11hEPloT_QEOJSIoiUjq6Fxc5iyFo"
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            rideData.map { it1 ->
                it1?.options?.map { it2 ->
                    DriverCard(
                        it2.name!!, it2.vehicle!!, it2.value.toString(),
                        onClick = {
                            it1.distance?.let {
                                it1.duration?.let { it3 ->
                                    it2.id?.let { it4 -> DriverDetails(id = it4, name = it2.name) }
                                        ?.let { it5 ->
                                            it2.value?.let { it4 ->
                                                viewModel.confirmRide(
                                                    context,
                                                    customerId = "1",
                                                    origin = (it1.origin?.latitude?.plus(it1.origin.longitude!!)).toString(),
                                                    destination = (it1.destination?.latitude?.plus(
                                                        it1.destination.longitude!!
                                                    )).toString(),
                                                    distance = it,
                                                    duration = it3,
                                                    driver = it5,
                                                    value = it4,
                                                    onSuccess = { response ->

                                                        confirmationResponse = "Success: $response"
                                                        println("Success: $response")
                                                    },
//                                                    onError = { error ->
//                                                        confirmationResponse = "Error: $error"
//                                                        println("Error: $error")
//                                                    }
                                                )
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
                .fillMaxHeight(), Arrangement.Top, Alignment.CenterHorizontally
        ) {            //== Botão de busca de rota
            Button(
                onClick = {
                    viewModel.fetchRideEstimate(
                        context,
                        customerId = customer_id.value,
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
    rideData.map {
        it?.options?.map { it1 ->
            it1.name?.let { it2 ->
                ConfirmationScreen(
                    name = it2,
                    onConfirm = { navigateToNextScreen() }, // Reseta a resposta
                    confirmationResponse = confirmationResponse
                )
            }
        }
    }
}

