package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.WeatherData
import com.github.johnnysc.ecp.domain.DomainException
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface WeatherInteractor : WeatherInCityUseCase, DefaultCityUseCase, WeatherDefaultCityUseCase {
    class Base(
        private val weatherRepository: WeatherRepository,
        private val weatherDataToMessageUIMapper: WeatherData.Mapper<MessageUI>,
        private val cityDataToMessageUIMapper: CityData.Mapper<MessageUI>,
        private val handleException: HandleError,
        private val domainExceptionToMessageUIMapper: DomainException.Mapper<MessageUI>
    ) : WeatherInteractor {

        override suspend fun getWeather(city: String): MessageUI {
            val result: MessageUI = try {
                weatherRepository.getWeatherInCity(city).map(weatherDataToMessageUIMapper)
            } catch (exception: Exception) {
                (handleException.handle(exception) as DomainException).map(
                    domainExceptionToMessageUIMapper
                )
            }
            return result
        }

        override suspend fun getWeather(): MessageUI {
            val result: MessageUI = try {
                weatherRepository.getWeatherInDefaultCity().map(weatherDataToMessageUIMapper)
            } catch (exception: Exception) {
                (handleException.handle(exception) as DomainException).map(
                    domainExceptionToMessageUIMapper
                )
            }
            return result
        }

        override suspend fun setDefault(newCity: String): MessageUI {
            val result: MessageUI = try {
                weatherRepository.saveDefaultCity(newCity).map(cityDataToMessageUIMapper)
            } catch (exception: Exception) {
                (handleException.handle(exception) as DomainException).map(
                    domainExceptionToMessageUIMapper
                )
            }
            return result
        }
    }
}