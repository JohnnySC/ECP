package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface CityDomain {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val title: String,
        private val longitude: Float = -1F,
        private val latitude: Float = -1F
    ) : CityDomain {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(title, longitude, latitude)
    }

    interface Mapper<T> {
        fun map(title: String, longitude: Float, latitude: Float): T

        class BaseToCityData : Mapper<CityData> {
            override fun map(title: String, longitude: Float, latitude: Float) =
                CityData.Base(title, longitude, latitude)

        }

        class BaseToTitle:Mapper<String>
        {
            override fun map(title: String, longitude: Float, latitude: Float)=title

        }

        class BaseToCoordinates:Mapper<Pair<Float,Float>>
        {
            override fun map(title: String, longitude: Float, latitude: Float)=Pair(longitude,latitude)

        }

        class BaseToMessageUi(private val manageResources: ManageResources):Mapper<MessageUI>
        {
            override fun map(title: String, longitude: Float, latitude: Float)=MessageUI.Ai(manageResources.string(
                R.string.set_weather_command_success))
        }

    }

}