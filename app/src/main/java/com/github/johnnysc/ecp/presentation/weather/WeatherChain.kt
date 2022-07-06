package com.github.johnnysc.ecp.presentation.weather

import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.domain.weather.GetWeatherInCityUseCase
import com.github.johnnysc.ecp.domain.weather.WeatherInteractor
import com.github.johnnysc.ecp.presentation.commands.Command
import com.github.johnnysc.ecp.presentation.commands.HandleUseCase
import com.github.johnnysc.ecp.presentation.messages.FeatureChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI
import com.github.johnnysc.ecp.presentation.weather.commands.WeatherInCityCommand
import com.github.johnnysc.ecp.presentation.weather.commands.WeatherSetCityCommand

class WeatherChain(
    private val interactor: WeatherInteractor,
    private val manageResources: ManageResources,
    private val commands: List<Command<WeatherInteractor>>
//    = listOf(
//        WeatherInCityCommand(manageResources),
//        WeatherSetCityCommand(manageResources)
//    )
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

interface Parser<T> : Mapper<String, DomainObject<T>?> {

    class Weather(private val manageResources: ManageResources) : Parser<GetWeatherInCityUseCase> {
        override fun map(data: String): DomainObject<GetWeatherInCityUseCase>? {
            if (data == manageResources.string(R.string.weather_no_default_city)) {//todo weatherlike
                return WeatherInCityNotMentioned
            }
            return null
        }
    }
}

class WeatherInCityCommandNew(
    private val parser: Parser.Weather
) : Command<WeatherInteractor> {

    private var domainObject: DomainObject<GetWeatherInCityUseCase>? = null

    override suspend fun handle(useCase: WeatherInteractor): MessageUI {
        return domainObject!!.handle(useCase)
    }

    override fun canHandle(message: String): Boolean {
        domainObject = parser.map(message)
        return if (domainObject == null) {
            //domainObject = secondparser.map(message)
            domainObject != null
        } else true
    }
}

abstract class DomainObject<T> : HandleUseCase<T>//todo rename

object WeatherInCityNotMentioned : DomainObject<GetWeatherInCityUseCase>() {
    override suspend fun handle(useCase: GetWeatherInCityUseCase): MessageUI =
        MessageUI.Ai(useCase.getWeather())
}

class WeatherInCity(private val city: String) : DomainObject<GetWeatherInCityUseCase>() {
    override suspend fun handle(useCase: GetWeatherInCityUseCase): MessageUI =
        MessageUI.Ai(useCase.getWeather(city))
}