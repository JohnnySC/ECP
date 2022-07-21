package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.data.weather.BaseWeatherRepository
import com.github.johnnysc.ecp.data.weather.CityData
import com.github.johnnysc.ecp.data.weather.cloud.RemoteWeather
import com.github.johnnysc.ecp.domain.weather.WeatherRepository

interface ProvideWeatherRepository {
    fun provideWeatherRepository(): WeatherRepository

    class Base(
        private val provideWeatherCloud: ProvideWeatherCloudDataSource,
        private val provideCityPreferenceDataStore: ProvideCacheDataSource
    ) : ProvideWeatherRepository {
        override fun provideWeatherRepository() = BaseWeatherRepository(
            provideWeatherCloud.provideCloudDataSource(),
            provideCityPreferenceDataStore.provideCacheDataSource(),
            RemoteWeather.Mapper.Base(),
            CityData.Mapper.Base()
        )
    }
}


