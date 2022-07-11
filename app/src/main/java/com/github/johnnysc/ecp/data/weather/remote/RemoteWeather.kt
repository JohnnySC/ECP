package com.github.johnnysc.ecp.data.weather.remote

interface RemoteWeather {
    fun <T>map(mapper:Mapper<T>):T

    fun isEmpty():Boolean

    class Base()
    {

    }
    interface Mapper<T>
    {
        fun map(temperature:Float):T
    }
}