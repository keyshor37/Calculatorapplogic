package com.example.myfinalcalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KmToMilesScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    var kmText by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Kilometer to Mile Converter",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = kmText,
            onValueChange = { kmText = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Kilometers") },
            singleLine = true
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                val km = kmText.toDoubleOrNull()
                resultText = if (km != null) {
                    val miles = km * 0.621371
                    "Miles: $miles"
                } else {
                    "Please enter a valid number"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convert")
        }

        Spacer(Modifier.height(16.dp))

        Text(resultText)

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to calculator")
        }
    }
}
