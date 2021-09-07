package ru.samitin.lesson1.viewModel

import ru.samitin.lesson1.repository.PODServerResponseData

sealed class PictureOfTheDayData{
    data class Success(val serverResponseData: PODServerResponseData) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()

}
