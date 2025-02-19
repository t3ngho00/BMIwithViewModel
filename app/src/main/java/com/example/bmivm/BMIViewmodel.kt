package com.example.bmivm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Locale


class BmiViewModel : ViewModel() {
    var heightInput by mutableStateOf("")
    var weightInput by mutableStateOf("")

    private val height: Double?
        get() = heightInput.toDoubleOrNull()?.div(100)

    private val weight: Double?
        get() = weightInput.toDoubleOrNull()

    val bmiValue: Double?
        get() {
            val h = height ?: return null
            val w = weight ?: return null
            if (h == 0.0) return null
            return w / (h * h)
        }

    val bmiText: String
        get() = bmiValue?.let { String.format(Locale.US, "%.2f", it) } ?: "Invalid input"

    val bmiCategory: String
        get() {
            val bmi = bmiValue ?: return "Invalid input"
            return when {
                bmi < 18.5 -> "Underweight"
                bmi < 25 -> "Healthy weight"
                else -> "Overweight"
            }
        }
}

