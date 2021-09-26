package ru.samitin.lesson1.viewModel

import ru.samitin.lesson1.repository.EarthEpicServerResponseData
import ru.samitin.lesson1.repository.MarsPhotosServerResponseData
import ru.samitin.lesson1.repository.PODServerResponseData
import ru.samitin.lesson1.repository.SolarFlareResponseData

sealed class AppState {
    data class SuccessPOD(val serverResponseData: PODServerResponseData) : AppState()
    data class SuccessEarthEpic (val serverResponseData: List<EarthEpicServerResponseData>) : AppState()
    data class SuccessMars(val serverResponseData: MarsPhotosServerResponseData) : AppState()
    data class SuccessWeather(val solarFlareResponseData:List<SolarFlareResponseData>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}