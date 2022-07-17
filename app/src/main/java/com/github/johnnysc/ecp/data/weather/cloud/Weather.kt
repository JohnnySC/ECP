package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.coremvvm.core.Read
import com.google.gson.annotations.SerializedName

interface Weather : Read<Float> {

    data class Base(
        @SerializedName("currentConditions") val currentWeather: CurrentWeather.Base
    ) : Weather {


        override fun read(): Float = currentWeather.read()
    }
}

