package com.github.johnnysc.ecp.data.weather.cloud

import com.github.johnnysc.coremvvm.core.Read
import com.google.gson.annotations.SerializedName

interface CurrentWeather : Read<Float>, IsEmpty {

    data class Base(
        @SerializedName("temp") private val temp: Float?
    ) : CurrentWeather {

        override fun isEmpty(): Boolean = temp == null

        override fun read(): Float = temp!!
    }
}



