package com.example.shopper.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.map
import com.example.shopper.models.RideEstimateModel
import com.example.shopper.viewmodel.RideViewModel


@Composable
fun RideForm(viewModel: RideViewModel, navigateToNextScreen: () -> Unit) {
    val origin =
        remember { mutableStateOf("Av. Pres. Kenedy, 2385 - Remédios, Osasco - SP, 02675-031") }
    val destination =
        remember { mutableStateOf("Av. Paulista, 1538 - Bela Vista, São Paulo - SP, 01310-200") }
    val customer_id = remember { mutableStateOf("2") }
    val driverId = 0
    val context = LocalContext.current
    val state = rememberScrollState()

    // --== Armazenando itens locais do motorista
    val _motorista = remember {
        mutableStateOf<List<RideEstimateModel>>(listOf())
    }

    val rideData = viewModel.rideEstimate.value
    val rideDetail = viewModel.motorista_api.observeAsState()
//    val rideData = viewModel._rideData.observeAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            rideData.map { it1 ->
                it1?.options?.map { it2 ->
                    DriverCard(
                        model = it1,

//                        it2.name!!, it2.vehicle!!, it2.value.toString(),
                        onClick = {
                            navigateToNextScreen.invoke()
//                            DriverDetailsCard(
//                                model = it1,
//                                onClick = { model ->
//
//
//                                }
//                            )

                        },
                    )
                }
            }
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
                .height(300.dp)
        ) {
            ShopperTextField(
                widthFloat = 1f,
                textAlign = TextAlign.Left,
                maxLength = 150,
                refValue = customer_id,
                onValueChange = {
                    customer_id.value
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

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(), Arrangement.Top, Alignment.CenterHorizontally
        ) {            //== Botão de busca de rota
            Button(
                onClick = {
                    viewModel.fetchRideEstimate(
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
}

