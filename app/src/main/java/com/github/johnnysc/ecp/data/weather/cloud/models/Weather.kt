package com.github.johnnysc.ecp.data.weather.cloud.models

import com.google.gson.annotations.SerializedName

interface Weather {

    fun <T> map(mapper: Mapper<T>): T

    fun isEmpty(): Boolean

    data class Base(
        @SerializedName("currentConditions") private val currentWeather: CurrentWeather,
        private val currentMapper: CurrentWeather.Mapper<Float>
    ) : Weather {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(currentWeather.map(currentMapper))

        override fun isEmpty(): Boolean = currentWeather.isEmpty()
    }

    interface Mapper<T> {

        fun map(temperature: Float): T

        class Base : Mapper<Float> {

            override fun map(temperature: Float): Float = temperature
        }
    }
}