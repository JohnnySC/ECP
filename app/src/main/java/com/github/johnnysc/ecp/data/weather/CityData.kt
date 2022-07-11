package com.github.johnnysc.ecp.data.weather

interface CityData {
    fun <M> map(mapper:Mapper<M>):M

    class Base(private val title: String):CityData
    {
        override fun <M> map(mapper: Mapper<M>)=mapper.map(title)


    }
    interface Mapper<M>
    {
        fun map(title:String):M
    }


}