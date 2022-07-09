package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface CityDomain {
    fun <T> map(mapper: Mapper<T>): T

    class Base(private val cityName: String) : CityDomain {
        override fun <T> map(mapper: Mapper<T>)=mapper.map(cityName)
    }

    interface Mapper<T> {
        fun map(cityName: String): T

        class Base(private val manageResources: ManageResources) : Mapper<MessageUI> {
            override fun map(cityName: String) =
                MessageUI.Ai(manageResources.string(R.string.set_weather_command_success))
        }
    }
}