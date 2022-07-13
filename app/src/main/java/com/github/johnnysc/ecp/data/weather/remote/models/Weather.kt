package com.github.johnnysc.ecp.data.weather.remote.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("currentConditions") val currentWeather: CurrentWeather?,
)
