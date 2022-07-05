package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.data.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

class WeatherChain(
    private val interactor: WeatherInteractor,
    private val commands: List<Command>
) : FeatureChain.CheckAndHandle {

    private var currentCommand: Command = Command.Empty()

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
        return currentCommand.handle(interactor).also { currentCommand = Command.Empty() }
    }
}