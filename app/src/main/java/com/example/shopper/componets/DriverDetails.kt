package com.example.shopper.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopper.models.Driver
import com.example.shopper.models.RideEstimateModel
import com.example.shopper.viewmodel.RideViewModel


@Composable
fun DriverDetailsCard(
    viewModel: RideViewModel,
    model: RideEstimateModel,
    onClick: ((model: RideEstimateModel) -> Unit)? = null,
    ) {
    // Simulando a busca de dados do motorista por ID
//    val driver = getDriverById(driverId) // Substitua com sua lógica de busca
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clickable { onClick?.invoke(model) }
        ) {
            model.duration?.let { it1 -> Text(text = it1) }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Preço: R$ ${model?.value}")

            Button(
                onClick = {

                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Escolher Motorista")
            }
        }
    }



//
//@Composable
//fun DriverDetailsCard(
//    viewModel: RideViewModel,
////    rideDetails: RideEstimateModel = RideEstimateModel(),
//    driverOptions: List<RideEstimateModel>,
//    onDriverSelected: (RideEstimateModel) -> Unit,
//    onBack: (() -> Unit?)? = null,
//) {
//    val rideData = viewModel.rideEstimate.value
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .shadow(8.dp, shape = RoundedCornerShape(12.dp))
//            .background(
//                androidx.compose.ui.graphics.Color.White,
//                shape = RoundedCornerShape(12.dp)
//            ),
//        contentAlignment = Alignment.TopStart
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//        ) {
//            // Voltar
//            Text(
//                text = "Voltar",
//                color = androidx.compose.ui.graphics.Color.Blue,
//                modifier = Modifier.clickable {
//                    if (onBack != null) {
//                        onBack()
//                    }
//                },
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Nome do motorista
//            items(driverOptions) { driver ->
//            rideData.map {
//                it?.options?.map {
//                    Text(
//                        text = "${it.name ?: "N/A"}  ",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = androidx.compose.ui.graphics.Color.Black,
//                        modifier = Modifier.padding(bottom = 8.dp)
//                    )
//                }
//            }
//
//            // Detalhes da corrida
//            Text(
//                text = "Distância: ${rideDetails?.distance ?: "N/A"} km",
//                fontSize = 18.sp,
//                color = androidx.compose.ui.graphics.Color.Gray
//            )
//            Text(
//                text = "Duração: ${rideDetails?.duration ?: "N/A"}",
//                fontSize = 18.sp,
//                color = androidx.compose.ui.graphics.Color.Gray,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            // Origem
//            rideDetails?.origin?.let {
//                Text(
//                    text = "Origem: ${it}",
//                    fontSize = 18.sp,
//                    color = androidx.compose.ui.graphics.Color.Black
//                )
//            }
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            // Destino
//            rideDetails?.destination?.let {
//                Text(
//                    text = "Destino: ${it}",
//                    fontSize = 18.sp,
//                    color = androidx.compose.ui.graphics.Color.Black
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Preço
//            Text(
//                text = "Preço: R$ ${rideDetails?.value ?: "N/A"}",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF4CAF50)
//            )
//        }
//
//    }
//}
//
