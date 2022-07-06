package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.CityParser
import com.github.johnnysc.ecp.presentation.weather.commands.WeatherInCityCommand
import com.github.johnnysc.ecp.presentation.weather.commands.WeatherParser
import com.github.johnnysc.ecp.presentation.weather.commands.WeatherSetCityCommand

class WeatherChain(
    private val interactor: WeatherInteractor,
    private val manageResources: ManageResources,
    private val commands: List<Command<WeatherInteractor>>
    = listOf(
        WeatherInCityCommand(WeatherParser(manageResources)),
        WeatherSetCityCommand(CityParser(manageResources))
    )
) : FeatureChain.CheckAndHandle {

    private var currentCommand: Command<WeatherInteractor> = Command.Empty()

    override fun canHandle(message: String): Boolean {
        val find = commands.find {
            it.canHandle(message)
        }
        find?.let {
            currentCommand = it
        }
        return find != null
    }

    override suspend fun handle(message: String): MessageUI {
        val result = currentCommand.handle(interactor)
        currentCommand = Command.Empty()
        return result
    }
}