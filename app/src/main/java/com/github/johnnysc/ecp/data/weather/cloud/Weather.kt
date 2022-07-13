package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.coremvvm.core.Read
import com.google.gson.annotations.SerializedName

interface Weather : Read<Float>, IsEmpty {

    data class Base(
        @SerializedName("currentConditions") private val currentWeather: CurrentWeather.Base
    ) : Weather {
        override fun isEmpty(): Boolean = currentWeather.isEmpty()

        override fun read(): Float = currentWeather.read()
    }
}

