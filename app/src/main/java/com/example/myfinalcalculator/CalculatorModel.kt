package com.example.myfinalcalculator

class CalculatorModel {

    private var left: Double? = null
    private var op: Char? = null

    fun clear() {
        left = null
        op = null
    }

    fun setOperator(currentValue: Double, operator: Char): Double {
        if (left == null) {
            left = currentValue
        } else if (op != null) {
            val res = calculate(left!!, currentValue, op!!)
            left = res
        }
        op = operator
        return left ?: currentValue
    }

    fun calculateResult(currentValue: Double): Double {
        val l = left
        val o = op
        if (l == null || o == null) return currentValue
        val res = calculate(l, currentValue, o)
        clear()
        return res
    }

    private fun calculate(a: Double, b: Double, op: Char): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> if (b != 0.0) a / b else Double.NaN
            else -> b
        }
    }

    fun format(value: Double): String {
        return if (value.isNaN()) {
            "Error"
        } else if (value == value.toLong().toDouble()) {
            value.toLong().toString()
        } else {
            value.toString()
        }
    }
}
