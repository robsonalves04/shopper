package com.example.shopper.componets


import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

import com.example.shopper.viewmodel.ShopperViewModel


@Composable
fun OnboardForm(dadosViewModel: ShopperViewModel,  navigateToNextScreen: () -> Unit) {

    val digiteID = remember { mutableStateOf("") }
    val digitesenha = remember { mutableStateOf("") }
    val paginaVazia = remember { mutableStateOf(0) }
    val context = LocalContext.current
    //==Tela Inicial
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFFCACFC5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //== Logo da Empresa
        Box {
            Image(
                painter = painterResource(id = R.drawable.travel_request),
                contentDescription = null,
                modifier = Modifier
                    .height(400.dp)
                    .width(400.dp)
                    .padding(top = 80.dp)
            )
        }

        Column() {

            ShopperTextField(
                widthFloat = 1f,
                textAlign = TextAlign.Center,
                maxLength = 8,
                refValue = digiteID,
                onValueChange = {
                    digiteID.value = it
//                    if (it.isEmpty()) {
//                        paginaVazia.value = 0
//                    }
                },
                placeholder = "Digite seu ID",
                tipoText = KeyboardType.Number
            )

            ShopperTextField(
                widthFloat = 1f,
                textAlign = TextAlign.Center,
                maxLength = 8,
                refValue = digitesenha,
                onValueChange = {
                    digitesenha.value = it
                    if (it.isEmpty()) {
                        paginaVazia.value = 0
                    }
                },
                placeholder = "Digite sua senha",
                tipoText = KeyboardType.Number
            )


        }
        Box {
            //== Botão de acesso ao aplicativo
            Button(
                onClick = {

                    dadosViewModel.setUserData(digiteID.value, digitesenha.value)
                    navigateToNextScreen()

                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2A7693)
                ),
                modifier = androidx.compose.ui.Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.8f)
                    .height(60.dp)
                    .align(Alignment.Center)
            ) {
                Text(text = "Entrar", fontSize = 22.sp, color = Color(0xFF09202D))
            }
        }
    }
}
