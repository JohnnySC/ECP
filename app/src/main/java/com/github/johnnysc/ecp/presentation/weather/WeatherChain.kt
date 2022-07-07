package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.setdefault.ParseCity
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.WeatherInCityCommand
import com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault.ParseDefaultWeather
import com.github.johnnysc.ecp.presentation.weather.commands.setdefault.WeatherSetCityCommand
import com.github.johnnysc.ecp.presentation.weather.commands.weatherdefault.WeatherDefaultCommand
import com.github.johnnysc.ecp.presentation.weather.commands.weatherincity.ParseWeatherInCity

class WeatherChain(
    private val interactor: WeatherInteractor,
    private val manageResources: ManageResources,
    private val commands: List<Command<WeatherInteractor>>
    = listOf(
        WeatherInCityCommand(ParseWeatherInCity(manageResources)),
        WeatherDefaultCommand(ParseDefaultWeather(manageResources)),
        WeatherSetCityCommand(ParseCity(manageResources))
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