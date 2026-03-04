package com.example.myfinalcalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    model: CalculatorModel,
    onOpenConverter: () -> Unit
) {
    var display by remember { mutableStateOf("0") }
    var isNewEntry by remember { mutableStateOf(true) }

    fun currentValue(): Double = display.toDoubleOrNull() ?: 0.0

    fun inputDigit(d: String) {
        display = if (isNewEntry || display == "0") d else display + d
        isNewEntry = false
    }

    fun inputDot() {
        if (isNewEntry) {
            display = "0."
            isNewEntry = false
        } else if (!display.contains(".")) {
            display += "."
        }
    }

    fun clearAll() {
        display = "0"
        isNewEntry = true
        model.clear()
    }

    fun setOperator(c: Char) {
        val res = model.setOperator(currentValue(), c)
        display = model.format(res)
        isNewEntry = true
    }

    fun equals() {
        val res = model.calculateResult(currentValue())
        display = model.format(res)
        isNewEntry = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = display,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            fontSize = 40.sp,
            textAlign = TextAlign.End
        )

        val rows = listOf(
            listOf("C", "÷", "×", "⌫"),
            listOf("7", "8", "9", "-"),
            listOf("4", "5", "6", "+"),
            listOf("1", "2", "3", "="),
            listOf("0", ".", "", "")
        )

        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                row.forEach { label ->
                    if (label.isBlank()) {
                        Spacer(
                            Modifier
                                .weight(1f)
                                .height(56.dp)
                        )
                    } else {
                        Button(
                            onClick = {
                                when (label) {
                                    "C" -> clearAll()
                                    "⌫" -> {
                                        if (!isNewEntry && display.length > 1) {
                                            display = display.dropLast(1)
                                        } else {
                                            display = "0"
                                            isNewEntry = true
                                        }
                                    }
                                    "." -> inputDot()
                                    "+" -> setOperator('+')
                                    "-" -> setOperator('-')
                                    "×" -> setOperator('*')
                                    "÷" -> setOperator('/')
                                    "=" -> equals()
                                    else -> inputDigit(label)
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                        ) {
                            Text(label, fontSize = 20.sp)
                        }
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onOpenConverter,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Km → Miles converter", fontSize = 18.sp)
        }
    }
}
