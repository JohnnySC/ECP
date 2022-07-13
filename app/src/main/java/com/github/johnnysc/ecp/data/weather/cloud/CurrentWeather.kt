package com.github.johnnysc.ecp.data.weather.cloud

import com.google.gson.annotations.SerializedName

interface CurrentWeather : Mapper<Float>, IsEmpty {

    data class Base(
        @SerializedName("temp") private val temp: Float?
    ) : CurrentWeather {

        override fun isEmpty(): Boolean = temp == null

        override fun map(): Float = temp!!
    }
}



