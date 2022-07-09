package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.ecp.domain.DomainException
import com.github.johnnysc.ecp.domain.ExceptionChain
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface WeatherInteractor : WeatherInCityUseCase, DefaultCityUseCase, WeatherDefaultCityUseCase {

    abstract class AbstractBaseInteractor(
        private val handleException: ExceptionChain,
        private val domainExceptionToMessageUIMapper: DomainException.Mapper<MessageUI>
    ) : WeatherInteractor {

        suspend fun handle(executeRequest: suspend () -> MessageUI): MessageUI {
            val result: MessageUI = try {
                executeRequest.invoke()
            } catch (exception: Exception) {
                handleException.handle(exception).map(
                    domainExceptionToMessageUIMapper
                )
            }
            return result
        }
    }

    class Base(
        private val weatherRepository: WeatherRepository,
        private val weatherDomainToMessageUIMapper: WeatherDomain.Mapper<MessageUI>,
        private val cityDomainToMessageUIMapper: CityDomain.Mapper<MessageUI>,
        handleException: ExceptionChain,
        domainExceptionToMessageUIMapper: DomainException.Mapper<MessageUI>
    ) : AbstractBaseInteractor(handleException, domainExceptionToMessageUIMapper) {

        override suspend fun getWeather(city: String) = handle {
            weatherRepository.getWeatherInCity(city).map(weatherDomainToMessageUIMapper)
        }


        override suspend fun getWeather() = handle {
            weatherRepository.getWeatherInDefaultCity().map(weatherDomainToMessageUIMapper)
        }


        override suspend fun setDefault(newCity: String) =
            handle { weatherRepository.saveDefaultCity(newCity).map(cityDomainToMessageUIMapper) }
    }
}