package com.github.johnnysc.ecp.data.weather.cloud.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("currentConditions") val currentWeather: CurrentWeather?,
)
