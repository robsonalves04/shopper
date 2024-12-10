package com.example.shopper.componets

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopper.R

import com.example.shopper.viewmodel.RideViewModel
import com.example.shopper.viewmodel.ShopperViewModel

@Composable
fun SearchForm(dadosViewModel: ShopperViewModel) {
    val context = LocalContext.current
    val userId = dadosViewModel.userId
    val digiteCep = remember { mutableStateOf("") }
    val paginaVazia = remember { mutableStateOf(0) }
    //==Tela Inicial
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFFCACFC5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //== Logo da Empresa
        Box {
            Column(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.travel_request),
                    contentDescription = null,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .padding(top = 10.dp)
                )
            }
        }

        Column {
            Text(text = "ID: $userId")

        }
        Box {


            Column(
                Modifier
                    .fillMaxWidth()
                    .height(450.dp)) {

                ShopperTextField(
                    widthFloat = 0.5f,
                    textAlign = TextAlign.Center,
                    maxLength = 8,
                    refValue = digiteCep,
                    onValueChange = {
                        digiteCep.value = it
                        if (it.isEmpty()) {
                            paginaVazia.value = 0
                        }
                    },
                    placeholder = "Digite o Cep",
                    tipoText = KeyboardType.Number
                )
            }



            StaticMapScreen(
                latitudeA = -23.550520, // Exemplo: São Paulo
                longitudeA = -46.633308,
                latitudeB = -22.906847, // Exemplo: Rio de Janeiro
                longitudeB = -43.172897,
                apiKey = "AIzaSyB4JS11hEPloT_QEOJSIoiUjq6Fxc5iyFo"
            )


            //== Botão de acesso ao aplicativo
            Button(
                onClick = {
//                    val intent = Intent(context, ScreenSearchCar::class.java)
//                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2A7693)
                ),
                modifier = androidx.compose.ui.Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
                    .height(60.dp)
                    .align(
                        Alignment.BottomCenter
                    )
            ) {
                Text(text = "Entrar", fontSize = 22.sp, color = Color(0xFF09202D))
            }
        }
    }
}

