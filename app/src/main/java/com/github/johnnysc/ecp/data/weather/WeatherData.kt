package com.github.johnnysc.ecp.data.weather

interface WeatherData {

    fun <T> map(mapper: Mapper<T>): T

    class Base(private val temperature: Float) : WeatherData {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(temperature)
    }

    interface Mapper<T> {
        fun map(temperature: Float): T
    }
}