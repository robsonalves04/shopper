package com.example.shopper.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopper.viewmodel.RideViewModel
import com.example.shopper.viewmodel.ShopperViewModel


@Composable
fun HistoricRidesForm(
    dadosViewModel: ShopperViewModel,
    viewModel: RideViewModel,
    navigateToNextScreen: () -> Unit
) {
    val customerId = remember { mutableStateOf(dadosViewModel.userId) }
    val scaffoldState = rememberScaffoldState()
    val selectedIndex = remember { mutableStateOf(0) }
    val rideData = viewModel.rideHistoric.value
    val context = LocalContext.current


    Column(modifier = Modifier.fillMaxSize()) {
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
                        selected = selectedIndex.value == 1,
                        onClick = {
                            selectedIndex.value = 1
                        }
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Histórico",
                                tint = Color(0xFF10100F)
                            )
                        },
                        label = {
                            Text(
                                "Histórico",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        },
                        selected = selectedIndex.value == 2,
                        onClick = {
                            selectedIndex.value = 2
                        }
                    )
                }
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Corridas Realizadas",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ShopperTextField(
                        widthFloat = 1f,
                        textAlign = TextAlign.Left,
                        maxLength = 150,
                        refValue = customerId,
                        onValueChange = {
                            dadosViewModel.userId = it
                        },
                        placeholder = "Digite seu ID",
                        tipoText = KeyboardType.Text
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            customerId.value.let {
                                viewModel.historicRides(context, it, "1")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF2A7693)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text(
                            text = "Buscar Corridas",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    rideData.map { ride ->
                        ride?.rides?.map { ride1 ->
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = "Origem: ${ride1.origin}")
                                Text(text = "Destino: ${ride1.destination}")
                                Text(text = "Distância: ${ride1.distance} km")
                                Text(text = "Duração: ${ride1.duration}")
                                Text(text = "Motorista: ${ride1.driver?.name}")
                                Text(text = "Valor: R$ ${ride1.value}")
                            }
                        }
                    }
                }
            }
        )
    }
}