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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopper.R


@Composable
fun OnboardForm() {
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
                painter = painterResource(id = R.drawable.travel_request), contentDescription = null,
                modifier = Modifier
                    .height(400.dp)
                    .width(400.dp)
                    .padding(top = 200.dp)
            )
        }
        Box {
            //== Botão de acesso ao aplicativo
            Button(
                onClick = {
                    val intent = Intent(context, ScreenSearchCar::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF2A7693)
                ),
                modifier = androidx.compose.ui.Modifier.padding(16.dp).fillMaxWidth(0.8f).height(60.dp).align(Alignment.Center)
            ) {
                Text(text = "Entrar", fontSize = 22.sp, color = Color(0xFF09202D) )
            }
        }
    }
}
