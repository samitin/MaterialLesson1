package ru.samitin.lesson1.viewModel

import ru.samitin.lesson1.repository.marsWeather.MarsPhotosResponseData

sealed class MarsPhotosData {
    data class Success(val serverResponseData: MarsPhotosResponseData?) : MarsPhotosData()
    data class Error(val error: Throwable) : MarsPhotosData()
    object Loading : MarsPhotosData()
}
