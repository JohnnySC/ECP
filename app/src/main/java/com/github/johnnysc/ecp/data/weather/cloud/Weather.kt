package com.github.johnnysc.ecp.data.weather.cloud

import com.google.gson.annotations.SerializedName

interface Weather : Mapper<Float>, IsEmpty {

    data class Base(
        @SerializedName("currentConditions") private val currentWeather: CurrentWeather.Base
    ) : Weather {
        override fun isEmpty(): Boolean = currentWeather.isEmpty()

        override fun map(): Float = currentWeather.map()
    }
}

