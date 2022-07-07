package com.github.johnnysc.ecp.domain.weather

import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.WeatherData
import com.github.johnnysc.ecp.domain.DomainException
import com.github.johnnysc.ecp.presentation.messages.MessageUI

interface WeatherInteractor : WeatherInCityUseCase, DefaultCityUseCase, WeatherDefaultCityUseCase {
    class Base(
        private val cityRepository: CityRepository,
        private val weatherRepository: WeatherRepository,
        private val cityDataToCityDomainMapper: CityData.Mapper<CityDomain>,
        private val cityDomainToMessageUIMapper: CityDomain.Mapper<MessageUI>,
        private val weatherDataToWeatherDomain: WeatherData.Mapper<WeatherDomain>,
        private val weatherDomainToMessageUI: WeatherDomain.Mapper<MessageUI>,
        private val handleException: HandleError,
        private val domainExceptionToMessageUIMapper: DomainException.Mapper<MessageUI>
    ) : WeatherInteractor {
        override suspend fun getWeather(city: String): MessageUI {
            var result: MessageUI
            try {
                val cityDomain =
                    cityRepository.getCityCoordinatesByName(city).map(cityDataToCityDomainMapper)
                val weatherDomain =
                    weatherRepository.getWeatherInCity(cityDomain).map(weatherDataToWeatherDomain)
                result = weatherDomain.map(weatherDomainToMessageUI)
            } catch (exception: Exception) {
                result = (handleException.handle(exception) as DomainException).map(
                    domainExceptionToMessageUIMapper
                )
            }
            return result
        }

        override suspend fun getWeather(): MessageUI {
            var result: MessageUI
            try {
                val cityDomain = cityRepository.getDefaultCity().map(cityDataToCityDomainMapper)
                val weatherDomain =
                    weatherRepository.getWeatherInCity(cityDomain).map(weatherDataToWeatherDomain)
                result = weatherDomain.map(weatherDomainToMessageUI)
            } catch (exception: Exception) {
                result = (handleException.handle(exception) as DomainException).map(
                    domainExceptionToMessageUIMapper
                )
            }
            return result
        }

        override suspend fun setDefault(newCity: String): MessageUI {
            var result: MessageUI
            try {
                val cityDomain =
                    cityRepository.getCityCoordinatesByName(newCity).map(cityDataToCityDomainMapper)
                cityRepository.saveDefaultCity(cityDomain)
                result = cityDomain.map(cityDomainToMessageUIMapper)
            } catch (exception: Exception) {
                result = (handleException.handle(exception) as DomainException).map(
                    domainExceptionToMessageUIMapper
                )
            }
            return result
        }
    }
}