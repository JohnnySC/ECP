package com.github.johnnysc.ecp.data.weather

interface WeatherData {
    fun <M> map(mapper: Mapper<M>): M

    class Base(private val temperature: Float) : WeatherData {
        override fun <M> map(mapper: Mapper<M>) = mapper.map(temperature)

    }

    interface Mapper<M> {
        fun map(temperature: Float): M
    }
}