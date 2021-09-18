package ru.samitin.lesson1.viewModel

import ru.samitin.lesson1.repository.PODServerResponseData
import ru.samitin.lesson1.repository.SolarFlairResponseData

sealed class SolarFlairData {
    data class Success(val serverResponseData: List<SolarFlairResponseData>) : SolarFlairData()
    data class Error(val error: Throwable) : SolarFlairData()
    object Loading : SolarFlairData()
}