package com.github.johnnysc.ecp.data.weather.cloud.models

import com.google.gson.annotations.SerializedName

interface CurrentWeather {

    fun <T> map(mapper: Mapper<T>): T

    fun isEmpty(): Boolean

    data class Base(
        @SerializedName("temp") private val temp: Float?
    ) : CurrentWeather {

        override fun <T> map(mapper: Mapper<T>): T = TODO()

        override fun isEmpty(): Boolean = temp == null
    }

    interface Mapper<T> {

        fun map(temperature: CurrentWeather): T

        class Base : Mapper<Weather> {

            override fun map(temperature: CurrentWeather): Weather = TODO()
        }
    }
}



