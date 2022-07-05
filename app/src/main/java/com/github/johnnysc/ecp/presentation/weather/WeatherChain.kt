package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.WeatherInCityCommand
import com.github.johnnysc.ecp.presentation.weather.commands.WeatherSetCityCommand

class WeatherChain(
    private val interactor: WeatherInteractor,
    private val manageResources: ManageResources,
    private val commands: List<Command<out Any>> = listOf(
        WeatherInCityCommand(manageResources),
        WeatherSetCityCommand()
    )
) : FeatureChain.CheckAndHandle {

    private var currentCommand: Command<WeatherInteractor>? = null

    override fun canHandle(message: String): Boolean {
        var canHandle = false
        commands.find {
            it.canHandle(message)
        }?.let {
            currentCommand = it
            canHandle = true
        }
        return canHandle
    }

    override suspend fun handle(message: String): MessageUI {
        val result = currentCommand?.handle(interactor)
        currentCommand = null
        return result ?: MessageUI.AiError(manageResources.string(R.string.unexpected_error_message))
    }
}