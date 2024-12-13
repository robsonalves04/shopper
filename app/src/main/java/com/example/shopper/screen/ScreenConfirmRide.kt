package com.example.shopper.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmationScreen(
    name: String,
    onConfirm: () -> Unit,
    confirmationResponse: String?
) {
    var showConfirmation by remember { mutableStateOf(false) }

    // Exibe a confirmação após sucesso
    LaunchedEffect(confirmationResponse) {
        if (confirmationResponse != null) {
            showConfirmation = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Exibe o Card de Confirmação quando showConfirmation for true
        if (showConfirmation) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        " Sua corrida foi confirmada com motorista",
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        "${name}",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Aguarde no local solicitado",
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onConfirm,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF2A7693)
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(0.3f)
                            .height(35.dp)
                    ) {
                        Text(
                            text = "Fechar",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = Color(0xFF09202D)
                        )
                    }
                }
            }
        }
    }
}