package com.github.johnnysc.ecp.data.weather.remote.models

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("temp") val temp: Float?
)