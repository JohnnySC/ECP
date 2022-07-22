package com.github.johnnysc.ecp.sl.weather

import com.github.johnnysc.ecp.core.Converter
import com.github.johnnysc.ecp.core.ConverterRawResourceToPoJo
import com.github.johnnysc.ecp.core.ReadRawResource
import com.github.johnnysc.ecp.data.weather.cloud.RemoteWeather
import com.github.johnnysc.ecp.data.weather.cloud.Weather
import com.github.johnnysc.ecp.data.weather.cloud.WeatherCloudDataSource
import com.github.johnnysc.ecp.sl.ProvideConvertRawResourceToPojoAdapter

class ProvideWeatherConverterRawToPojo(
    converter: Converter,
    readRawResource: ReadRawResource
) : ProvideConvertRawResourceToPojoAdapter<Weather.Base, RemoteWeather>(
    readRawResource,
    converter
) {
    override fun provideConvertRawResourceToPojoAdapter(): ConverterRawResourceToPoJo<Weather.Base, RemoteWeather> {
        return WeatherCloudDataSource.Mock.FetchWeather(converter, readRawResource)
    }
}