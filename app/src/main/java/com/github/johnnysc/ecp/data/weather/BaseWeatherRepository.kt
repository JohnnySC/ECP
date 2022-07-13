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
    private val weatherRemoteToWeatherDomainMapper: RemoteWeather.Mapper<WeatherDomain>,
    private val cityDataToTitleMapper: CityData.Mapper<String>,
    handleError: HandleError
) : AbstractRepository(handleError), WeatherRepository {

    override suspend fun getWeatherInCity(city: String): WeatherDomain = handle {
        val result = remoteWeatherDataSource.getWeather(city)
        if (result.isEmpty())
            throw ThereIsNoCityWithSuchTitleException()
        result.map(weatherRemoteToWeatherDomainMapper)
    }

    override suspend fun getWeatherInDefaultCity(): WeatherDomain = handle {
        remoteWeatherDataSource.getWeather(localCityDataSource.getDefaultCity().map(cityDataToTitleMapper))
            .map(weatherRemoteToWeatherDomainMapper)
    }

    override suspend fun saveDefaultCity(newCity: String): CityDomain = handle {
        if (remoteWeatherDataSource.getWeather(newCity).isEmpty()) {
            throw ThereIsNoCityWithSuchTitleException()
        }
        localCityDataSource.saveDefaultCity(newCity).map(cityDataToCityDomainMapper)
    }
}