package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherChain(
    private val commands: List<Command<WeatherInteractor>>,
    private val interactor: WeatherInteractor,
    private val manageResources: ManageResources
) : FeatureChain.CheckAndHandle {

    private var currentCommand: Command<WeatherInteractor>? = null

    override fun canHandle(message: String): Boolean {
        commands.forEach {
            if (it.canHandle(message))
                currentCommand = it
        }
        return currentCommand != null
    }

    override suspend fun handle(message: String): MessageUI {
        return (currentCommand?.handle(interactor)
            ?: MessageUI.AiError(manageResources.string(R.string.unexpected_error_message))).also {
            currentCommand = null
        }
    }
}