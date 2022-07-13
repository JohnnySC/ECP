package com.github.johnnysc.ecp.data.weather


interface CityData {

    fun <T> map(mapper: Mapper<T>): T

    class Base(private val title: String) : CityData {

        override fun <T> map(mapper: Mapper<T>) = mapper.map(title)
    }

    interface Mapper<T> {

        fun map(title: String): T

        class Base : Mapper<String> {

            override fun map(title: String): String = title
        }
    }
}