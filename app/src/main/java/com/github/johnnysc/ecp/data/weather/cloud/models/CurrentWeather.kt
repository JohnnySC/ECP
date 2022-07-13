package com.github.johnnysc.ecp.data.weather.cloud.models

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("temp") val temp: Float?
)