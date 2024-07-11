package com.innominds.jetpackcompose.models

data class MedicalData(
    val time: Long,
    val oxygen: Float,
    val glucose: Float,
    val heartRate: Float,
    val temperature: Float
)