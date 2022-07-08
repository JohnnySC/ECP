package com.github.johnnysc.ecp.data.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.messages.MessageUI


interface WeatherData {

    fun <T> map(mapper: Mapper<T>): T

    data class Base(private val temperature: Float) : WeatherData {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(temperature)
    }

    interface Mapper<T> {
        fun map(temperature: Float): T

        class BaseToWeatherUI(private val manageResources: ManageResources) : Mapper<MessageUI>
        {
            override fun map(temperature: Float): MessageUI {
                return MessageUI.Ai(manageResources.string(R.string.weather_response).format(temperature))
            }
        }

    }
}