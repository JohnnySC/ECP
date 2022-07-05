package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.WeatherRepository
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherChain(
    private val manageResources: ManageResources,
    private val repository: WeatherRepository
) : FeatureChain.CheckAndHandle {

    private val triggers = manageResources.string(R.string.weather_triggers)

    override fun canHandle(message: String): Boolean {
        val prepared = message.split(" ")
        var canHandle = false
        prepared.forEach {
            canHandle = triggers.contains(it)
        }
        return canHandle
    }

    override suspend fun handle(message: String): MessageUI {
        return if (repository.isDefaultCitySet()) {
            MessageUI.Ai(
                String.format(
                    manageResources.string(R.string.weather_response),
                    repository.weatherInDefaultCity()
                )
            )
        } else {
            MessageUI.AiError(manageResources.string(R.string.weather_no_default_city))
        }
    }
}