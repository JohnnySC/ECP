package com.github.johnnysc.ecp.data.weather

import com.github.johnnysc.ecp.data.weather.cache.CityCacheDataSource
import com.github.johnnysc.ecp.data.weather.cloud.RemoteWeather
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.domain.weather.WeatherDomain
import com.github.johnnysc.ecp.domain.weather.WeatherRepository

class BaseWeatherRepository(
    private val weatherCloudDataSource: WeatherCloudDataSource,
    private val cityCacheDataSource: CityCacheDataSource,
    private val weatherRemoteToWeatherDomainMapper: RemoteWeather.Mapper<WeatherDomain>,
    private val cityDataToTitleMapper: CityData.Mapper<String>
) : WeatherRepository {

    override suspend fun getWeatherInCity(city: String): WeatherDomain =
        weatherCloudDataSource.getWeather(city).map(weatherRemoteToWeatherDomainMapper)

    override suspend fun getWeatherInDefaultCity(): WeatherDomain =
        getWeatherInCity(cityCacheDataSource.getDefaultCity().map(cityDataToTitleMapper))

    override suspend fun saveDefaultCity(newCity: String) {
        weatherCloudDataSource.getWeather(newCity)
        cityCacheDataSource.saveDefaultCity(newCity)
    }
}