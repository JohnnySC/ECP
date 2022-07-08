package com.github.johnnysc.ecp.data.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.messages.MessageUI


interface CityData {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val title: String,
        private val longitude: Float = -1F,
        private val latitude: Float = -1F
    ) : CityData {
        override fun <T> map(mapper: Mapper<T>) = mapper.map(title, longitude, latitude)

    }

    interface Mapper<T> {
        fun map(title: String, longitude: Float, latitude: Float): T

        class BaseToMessageUI(private val manageResources: ManageResources):Mapper<MessageUI>
        {
            override fun map(title: String, longitude: Float, latitude: Float): MessageUI {
                return MessageUI.Ai(manageResources.string(R.string.set_weather_command_success))
            }
        }
    }
}
