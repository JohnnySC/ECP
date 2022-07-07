package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface WeatherDomain {
    fun <T> map(mapper:Mapper<T>):T

    data class Base(private val temperature:Float):WeatherDomain
    {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(temperature)
    }

    interface Mapper<T>
    {
        fun map(temperature: Float):T

        class Base(private val manageResources: ManageResources):Mapper<MessageUI>
        {
            override fun map(temperature: Float): MessageUI {
                val template=manageResources.string(R.string.weather_response)
                val result=template.format(temperature)
                return MessageUI.Ai(text = result)

            }
        }
    }
}