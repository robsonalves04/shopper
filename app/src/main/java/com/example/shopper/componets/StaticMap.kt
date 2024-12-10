package com.example.shopper.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun StaticMapScreen(
    latitudeA: Double,
    longitudeA: Double,
    latitudeB: Double,
    longitudeB: Double,
    apiKey: String
) {
    val mapUrl = remember {
        "https://maps.googleapis.com/maps/api/staticmap?" +
                "size=600x400" +
                "&path=weight:5%7Ccolor:blue%7Cenc:YOUR_ENCODED_POLYLINE" +
                "&markers=color:red%7Clabel:A%7C$latitudeA,$longitudeA" +
                "&markers=color:green%7Clabel:B%7C$latitudeB,$longitudeB" +
                "&key=$apiKey"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(8.dp, shape = RoundedCornerShape(16.dp)) // Sombra arredondada
            .background(Color.White, shape = RoundedCornerShape(16.dp)), // Fundo branco arredondado
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(8.dp), // Margem interna
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagem do Mapa
            Image(
                painter = rememberAsyncImagePainter(model = mapUrl),
                contentDescription = "Mapa Estático",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f)
                    .clip(RoundedCornerShape(16.dp)) // Recorte arredondado para a imagem
                    .border(2.dp, Color.Gray, RoundedCornerShape(16.dp)) // Borda cinza fina
            )
        }
    }
}

