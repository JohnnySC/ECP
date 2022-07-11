package com.github.johnnysc.ecp.data.weather

import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.ecp.data.AbstractRepository
import com.github.johnnysc.ecp.data.weather.exceptions.ThereIsNoCityWithSuchTitleException
import com.github.johnnysc.ecp.data.weather.local.CityLocalDataSource
import com.github.johnnysc.ecp.data.weather.remote.RemoteWeather
import com.github.johnnysc.ecp.data.weather.remote.WeatherRemoteDataSource
import com.github.johnnysc.ecp.domain.weather.CityDomain
import com.github.johnnysc.ecp.domain.weather.WeatherDomain
import com.github.johnnysc.ecp.domain.weather.WeatherRepository

class BaseWeatherRepository(
    private val remoteWeatherDataSource: WeatherRemoteDataSource,
    private val localCityDataSource: CityLocalDataSource,
    private val cityDataToCityDomainMapper: CityData.Mapper<CityDomain>,
    private val weatherRemoteToWeatherDomain: RemoteWeather.Mapper<WeatherDomain>,
    private val cityDataToTitleMapper: CityData.Mapper<String>,
    handleError: HandleError
) : AbstractRepository(handleError), WeatherRepository {


    override suspend fun getWeatherInCity(city: String) = handle {
        return@handle remoteWeatherDataSource.getWeather(city).map(weatherRemoteToWeatherDomain)
    }

    override suspend fun getWeatherInDefaultCity() = handle {
        val defCity = localCityDataSource.getDefaultCity()
        return@handle remoteWeatherDataSource.getWeather(defCity.map(cityDataToTitleMapper))
            .map(weatherRemoteToWeatherDomain)
    }

    override suspend fun saveDefaultCity(newCity: String): CityDomain {
        if (remoteWeatherDataSource.getWeather(cityName = newCity).isEmpty()) {
            throw ThereIsNoCityWithSuchTitleException()
        } else
            return localCityDataSource.saveDefaultCity(newCity).map(cityDataToCityDomainMapper)
    }
}