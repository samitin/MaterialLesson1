package ru.samitin.lesson1.repository.marsWeather

import com.google.gson.annotations.SerializedName

data class MarsPhotosResponseData (
    @field:SerializedName("photos") val marsPhotos : List<MarsPhoto>?)

