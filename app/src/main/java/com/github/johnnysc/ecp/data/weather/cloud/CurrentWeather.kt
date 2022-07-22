package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.coremvvm.core.Read
import com.google.gson.annotations.SerializedName

interface CurrentWeather : Read<Float> {

    data class Base(
        @SerializedName("temp") val temp: Float?
    ) : CurrentWeather {

        override fun read(): Float = temp!!
    }
}



