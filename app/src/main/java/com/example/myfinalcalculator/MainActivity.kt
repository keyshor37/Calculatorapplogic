package com.example.myfinalcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


class MainActivity : ComponentActivity() {

    private val calculatorModel = CalculatorModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showConverter by remember { mutableStateOf(false) }

            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                if (showConverter) {
                    KmToMilesScreen(
                        modifier = Modifier.padding(innerPadding),
                        onBack = { showConverter = false }
                    )
                } else {
                    CalculatorScreen(
                        modifier = Modifier.padding(innerPadding),
                        model = calculatorModel,
                        onOpenConverter = { showConverter = true }
                    )
                }
            }
        }
    }

}


