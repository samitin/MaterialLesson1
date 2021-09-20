package ru.samitin.lesson1.repository.marsWeather

import com.google.gson.annotations.SerializedName

data class MarsPhoto(
    @field:SerializedName("img_src") val imgSrc : String?,
    @field:SerializedName("earth_date") val earthDate :String?
)
