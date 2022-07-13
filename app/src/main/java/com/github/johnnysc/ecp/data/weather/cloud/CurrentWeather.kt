package com.github.johnnysc.ecp.data.weather.cloud

import com.google.gson.annotations.SerializedName


interface CurrentWeather {

    fun map(): Float

    fun isEmpty(): Boolean

    data class Base(
        @SerializedName("temp") private val temp: Float?
    ) : CurrentWeather {

        override fun isEmpty(): Boolean = temp == null

        override fun map(): Float = temp!!
    }
}



